package br.com.vr.miniautorizador.service;

import br.com.vr.miniautorizador.database.entity.CartaoEntity;
import br.com.vr.miniautorizador.database.repository.CartaoRepository;
import br.com.vr.miniautorizador.exception.CartaoNaoEncontradoExcetion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static br.com.vr.miniautorizador.util.Constants.*;
import static java.math.BigDecimal.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@SpringBootTest
public class CartaoServiceTest {

    @Autowired
    private CartaoService service;

    @MockBean
    private CartaoRepository repository;

    @Test
    void quandoRegistrarJaPossuirCartaoEntaoRetornaCartaoDto() {
        given(this.repository.findByNumeroCartao(NUMERO_CARTAO))
                .willReturn(Optional.of(ENTITY));

        testeRegister(UNPROCESSABLE_ENTITY, 0);
    }

    @Test
    void quandoRegistrarNaoPossuirCartaoEntaoRetornaCartaoDto() {
        given(this.repository.findByNumeroCartao(NUMERO_CARTAO))
                .willReturn(Optional.empty());
        given(this.repository.save(any(CartaoEntity.class)))
                .willReturn(ENTITY);

        testeRegister(CREATED, 1);
    }

    private void testeRegister(HttpStatus unprocessableEntity, int times) {
        var retorno = service.register(CARTAO);

        assertThat(retorno.getBody()).isEqualTo(CARTAO);
        assertThat(retorno.getStatusCode()).isEqualTo(unprocessableEntity);
        verify(repository, times(times)).save(any(CartaoEntity.class));
    }

    @Test
    void quandoFindEncontrarCartaoRetornaSaldo() {
        given(this.repository.findByNumeroCartao(NUMERO_CARTAO))
                .willReturn(Optional.of(ENTITY));

        var retorno = service.find(NUMERO_CARTAO);
        assertThat(retorno).isEqualTo(ONE);
    }

    @Test
    void quandoFindEncontrarCartaoRetornaException() {
        given(this.repository.findByNumeroCartao(NUMERO_CARTAO))
                .willReturn(Optional.empty());

        Assertions.assertThrows(CartaoNaoEncontradoExcetion.class, () ->
                service.find(NUMERO_CARTAO), "CartaoNaoEncontradoExcetion error was expected");
    }
}
