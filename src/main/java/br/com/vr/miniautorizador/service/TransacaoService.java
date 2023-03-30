package br.com.vr.miniautorizador.service;

import br.com.vr.miniautorizador.database.entity.CartaoEntity;
import br.com.vr.miniautorizador.rest.dto.TransacaoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TransacaoService {

    private ValidaTransacaoService validate;
    private CartaoService cartaoService;

    public void execute(TransacaoDto request) {
        var entity = validaAutorizacao(request);
        entity.setSaldo(entity.getSaldo().subtract(request.getValor()));
        cartaoService.save(entity);
        log.info("Transação realizada com sucesso");
    }

    private CartaoEntity validaAutorizacao(TransacaoDto request) {
        var entity = validate.cartao(request.getNumeroCartao());
        validate.senha(entity, request.getSenha());
        validate.saldo(entity, request.getValor());
        return entity;
    }
}
