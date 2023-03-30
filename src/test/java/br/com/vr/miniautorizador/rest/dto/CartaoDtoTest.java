package br.com.vr.miniautorizador.rest.dto;

import org.junit.jupiter.api.Test;

import static br.com.vr.miniautorizador.util.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CartaoDtoTest {

    @Test
    void quandoInicializaCartaoDtoComCartaoEntity() {
        var cartaoDto = new CartaoDto(ENTITY);
        assertThat(cartaoDto.getNumeroCartao()).isEqualTo(NUMERO_CARTAO);
        assertThat(cartaoDto.getSenha()).isEqualTo(SENHA);
    }
}
