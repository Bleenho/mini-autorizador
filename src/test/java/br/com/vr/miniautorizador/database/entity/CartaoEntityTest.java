package br.com.vr.miniautorizador.database.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.com.vr.miniautorizador.util.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CartaoEntityTest {

    @Test
    void quandoInicializaCartaoEntityComCartaoDto() {
        var entity = new CartaoEntity(CARTAO);
        assertThat(entity.getNumeroCartao()).isEqualTo(NUMERO_CARTAO);
        assertThat(entity.getSenha()).isEqualTo(SENHA);
        assertThat(entity.getSaldo()).isEqualTo(BigDecimal.valueOf(500));
    }
}
