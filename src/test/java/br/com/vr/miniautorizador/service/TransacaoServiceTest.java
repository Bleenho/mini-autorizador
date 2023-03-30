package br.com.vr.miniautorizador.service;

import br.com.vr.miniautorizador.rest.dto.TransacaoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static br.com.vr.miniautorizador.util.Constants.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransacaoServiceTest {

    @Autowired
    private TransacaoService service;

    @MockBean
    private ValidaTransacaoService validate;

    @MockBean
    private CartaoService cartaoService;

    @Test
    void quandoExecutaTransacao() {
        given(validate.cartao(NUMERO_CARTAO))
                .willReturn(ENTITY);
        doNothing().when(validate).senha(ENTITY, SENHA);
        doNothing().when(validate).saldo(ENTITY, BigDecimal.ONE);
        doNothing().when(cartaoService).save(ENTITY);

        service.execute(new TransacaoDto(NUMERO_CARTAO, SENHA, BigDecimal.ONE));
        verify(validate, times(1)).cartao(NUMERO_CARTAO);
        verify(validate, times(1)).senha(ENTITY, SENHA);
        verify(validate, times(1)).saldo(ENTITY, BigDecimal.ONE);
        verify(cartaoService, times(1)).save(ENTITY);
    }
}
