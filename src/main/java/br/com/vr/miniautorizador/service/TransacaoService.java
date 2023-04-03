package br.com.vr.miniautorizador.service;

import br.com.vr.miniautorizador.database.entity.CartaoEntity;
import br.com.vr.miniautorizador.database.entity.TransacaoEntity;
import br.com.vr.miniautorizador.database.repository.TransacaoRepository;
import br.com.vr.miniautorizador.exception.TransacaoException;
import br.com.vr.miniautorizador.exception.constants.TransacaoExceptionEnum;
import br.com.vr.miniautorizador.rest.dto.TransacaoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class TransacaoService {

    private ValidaTransacaoService validate;
    private CartaoService cartaoService;
    private TransacaoRepository repository;

    public void execute(TransacaoDto request) {
        var entity = validate.cartao(request.getNumeroCartao());

        repository.findByCartaoIdAndFimIsNull(entity.getId()).stream().findFirst()
                .ifPresentOrElse(value -> {
                            throw new TransacaoException(TransacaoExceptionEnum.CONCORRENCIA_TRANSACAO);
                        }
                        , () -> {
                            var transacao = iniciarTransacao(entity.getId());
                            try {
                                realizarTransacao(entity, request);
                            } catch (Exception e) {
                                encerrarTransacao(transacao, e.getMessage());
                                log.error(e.getMessage());
                            }
                            encerrarTransacao(transacao, null);
                        });
    }

    private void encerrarTransacao(TransacaoEntity transacao, String erro) {
        transacao.setFim(LocalDateTime.now());
        transacao.setErro(erro);
        repository.save(transacao);
    }

    private void realizarTransacao(CartaoEntity entity, TransacaoDto request) {
        validate.senha(entity, request.getSenha());
        validate.saldo(entity, request.getValor());

        entity.setSaldo(entity.getSaldo().subtract(request.getValor()));
        cartaoService.save(entity);
    }

    private TransacaoEntity iniciarTransacao(Long idCartao) {
        var entity = new TransacaoEntity(idCartao);
        return repository.save(entity);
    }

}
