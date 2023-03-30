package br.com.vr.miniautorizador.rest.controller;

import br.com.vr.miniautorizador.exception.CartaoNaoEncontradoExcetion;
import br.com.vr.miniautorizador.rest.dto.CartaoDto;
import br.com.vr.miniautorizador.service.CartaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;

import static br.com.vr.miniautorizador.util.Constants.CARTAO;
import static br.com.vr.miniautorizador.util.Constants.NUMERO_CARTAO;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartaoController.class)
public class CartaoControllerTest {

    private static final String URI = "/cartoes";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartaoService service;

    @Test
    public void cadastrarCartaoQuandoRetornarSucesso() throws Exception {
        testeRegitrarCartao(HttpStatus.CREATED, status().isCreated());
    }

    @Test
    public void cadastrarCartaoQuandoRetornarCartaoJaEstaCadastrado() throws Exception {
        testeRegitrarCartao(HttpStatus.UNPROCESSABLE_ENTITY, status().isUnprocessableEntity());
    }

    private void testeRegitrarCartao(HttpStatus httpStatus, ResultMatcher resultMatcher) throws Exception {
        var response = ResponseEntity.status(httpStatus)
                .body(CARTAO);
        var cartaoJson = new ObjectMapper().writeValueAsString(CARTAO);
        when(service.cadastrar(any(CartaoDto.class))).thenReturn(response);

        this.mockMvc.perform(post(URI)
                        .contentType("application/json")
                        .content(cartaoJson))
                .andExpect(resultMatcher)
                .andExpect(content().json(cartaoJson));

        verify(service, times(1)).cadastrar(any(CartaoDto.class));
    }

    @Test
    public void consultarSaldoQuandoCartaoNaoEncontradoRetornarException() throws Exception {
        when(service.find(eq(NUMERO_CARTAO))).thenThrow(CartaoNaoEncontradoExcetion.class);

        this.mockMvc.perform(get(URI.concat("/").concat(NUMERO_CARTAO)))
                .andExpect(status().isNotFound());

        verify(service, times(1)).find(eq(NUMERO_CARTAO));
    }

    @Test
    public void consultarSaldoQuandoCartaoEncontradoRetornarSaldoSucesso() throws Exception {
        when(service.getSaldo(eq(NUMERO_CARTAO))).thenReturn(BigDecimal.ONE);

        this.mockMvc.perform(get(URI.concat("/").concat(NUMERO_CARTAO)))
                .andExpect(status().isOk())
                .andExpect(content().json(BigDecimal.ONE.toString()));

        verify(service, times(1)).find(eq(NUMERO_CARTAO));
    }

}
