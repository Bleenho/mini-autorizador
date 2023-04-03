package br.com.vr.miniautorizador.service;

import br.com.vr.miniautorizador.database.entity.TransacaoEntity;
import br.com.vr.miniautorizador.database.repository.TransacaoRepository;
import br.com.vr.miniautorizador.exception.TransacaoException;
import br.com.vr.miniautorizador.rest.dto.TransacaoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Collections;

import static br.com.vr.miniautorizador.exception.constants.TransacaoExceptionEnum.CONCORRENCIA_TRANSACAO;
import static br.com.vr.miniautorizador.util.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
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

    @MockBean
    private TransacaoRepository repository;

    @Test
    void quandoExecutaTransacao() {
        given(validate.cartao(NUMERO_CARTAO))
                .willReturn(ENTITY_COM_ID);
        doNothing().when(validate).senha(ENTITY_COM_ID, SENHA);
        doNothing().when(validate).saldo(ENTITY_COM_ID, BigDecimal.ONE);
        doNothing().when(cartaoService).save(ENTITY_COM_ID);
        given(repository.findByCartaoIdAndFimIsNull(ENTITY_COM_ID.getId()))
                .willReturn(Collections.emptyList());
        given(repository.save(any())).willReturn(new TransacaoEntity());

        service.execute(new TransacaoDto(NUMERO_CARTAO, SENHA, BigDecimal.ONE));
        verify(validate, times(1)).cartao(NUMERO_CARTAO);
        verify(validate, times(1)).senha(ENTITY_COM_ID, SENHA);
        verify(validate, times(1)).saldo(ENTITY_COM_ID, BigDecimal.ONE);
        verify(cartaoService, times(1)).save(ENTITY_COM_ID);
    }

    @Test
    void quandoExecutaTransacaoConcorrenciaException() {
        given(validate.cartao(NUMERO_CARTAO))
                .willReturn(ENTITY_COM_ID);
        doNothing().when(validate).senha(ENTITY_COM_ID, SENHA);
        doNothing().when(validate).saldo(ENTITY_COM_ID, BigDecimal.ONE);
        doNothing().when(cartaoService).save(ENTITY_COM_ID);
        given(repository.findByCartaoIdAndFimIsNull(ENTITY_COM_ID.getId()))
                .willReturn(Collections.singletonList(new TransacaoEntity()));
        given(repository.save(any())).willReturn(new TransacaoEntity());

        var exception = Assertions.assertThrows(TransacaoException.class, () ->
                        service.execute(new TransacaoDto(NUMERO_CARTAO, SENHA, BigDecimal.ONE)),
                "TransacaoException error was expected");

        assertThat(exception.getRegra()).isEqualTo(CONCORRENCIA_TRANSACAO);
    }
}
