package br.com.vr.miniautorizador.service;

import br.com.vr.miniautorizador.exception.TransacaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static br.com.vr.miniautorizador.exception.constants.TransacaoExceptionEnum.*;
import static br.com.vr.miniautorizador.util.Constants.ENTITY;
import static br.com.vr.miniautorizador.util.Constants.NUMERO_CARTAO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ValidaTransacaoServiceTest {

    @Autowired
    private ValidaTransacaoService service;

    @MockBean
    private CartaoService cartaoService;

    @Test
    void quandoConsultaCartaoComSucessoRetornaEntity() {
        given(cartaoService.find(NUMERO_CARTAO))
                .willReturn(ENTITY);
        var retorno = service.cartao(NUMERO_CARTAO);

        assertThat(retorno).isEqualTo(ENTITY);
    }

    @Test
    void quandoConsultaCartaoNaoEncontradoRetornaTransacaoException() {
        given(cartaoService.find(NUMERO_CARTAO))
                .willThrow(new TransacaoException(CARTAO_INEXISTENTE));

        TransacaoException exception = Assertions.assertThrows(TransacaoException.class, () ->
                service.cartao(NUMERO_CARTAO), "TransacaoException error was expected");

        assertThat(exception.getRegra()).isEqualTo(CARTAO_INEXISTENTE);
    }

    @Test
    void quandoConsultaCartaoSenhaNaoConfereRetornaTransacaoException() {
        TransacaoException exception = Assertions.assertThrows(TransacaoException.class, () ->
                service.senha(ENTITY, "23"), "TransacaoException error was expected");

        assertThat(exception.getRegra()).isEqualTo(SENHA_INVALIDA);
    }

    @Test
    void quandoConsultaCartaoSaldoInvalidoRetornaTransacaoException() {
        TransacaoException exception = Assertions.assertThrows(TransacaoException.class, () ->
                service.saldo(ENTITY, BigDecimal.valueOf(600)), "TransacaoException error was expected");

        assertThat(exception.getRegra()).isEqualTo(SALDO_INSUFICIENTE);
    }
}
