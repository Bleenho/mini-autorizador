package br.com.vr.miniautorizador.service;

import br.com.vr.miniautorizador.database.entity.CartaoEntity;
import br.com.vr.miniautorizador.exception.CartaoNaoEncontradoExcetion;
import br.com.vr.miniautorizador.exception.TransacaoException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.vr.miniautorizador.exception.constants.TransacaoExceptionEnum.*;

@Service
@AllArgsConstructor
@Slf4j
public class ValidaTransacaoService {

    private CartaoService cartaoService;

    public CartaoEntity cartao(String numeroCartao) {
        try {
            log.info("Validando cartão");
            return cartaoService.find(numeroCartao);
        } catch (CartaoNaoEncontradoExcetion ex) {
            throw new TransacaoException(CARTAO_INEXISTENTE);
        }
    }

    public void senha(CartaoEntity entity, String senha) {
        var optional = senha.equals(entity.getSenha()) ?
                Optional.of(senha) : Optional.empty();
        log.info("Validando Senha");
        optional.orElseThrow(() -> new TransacaoException(SENHA_INVALIDA));
    }

    public void saldo(CartaoEntity entity, BigDecimal valor) {
        var optional = entity.getSaldo().compareTo(valor) >= 0 ?
                Optional.of(valor) : Optional.empty();
        log.info("Validando Saldo");
        optional.orElseThrow(() -> new TransacaoException(SALDO_INSUFICIENTE));
    }
}
