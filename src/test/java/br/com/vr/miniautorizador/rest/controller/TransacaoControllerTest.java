package br.com.vr.miniautorizador.rest.controller;

import br.com.vr.miniautorizador.rest.dto.TransacaoDto;
import br.com.vr.miniautorizador.service.TransacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static br.com.vr.miniautorizador.util.Constants.NUMERO_CARTAO;
import static br.com.vr.miniautorizador.util.Constants.SENHA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransacaoController.class)
public class TransacaoControllerTest {

    private static final String URI = "/transacoes";
    private static final TransacaoDto TRANSACAO_REQUEST =
            new TransacaoDto(NUMERO_CARTAO, SENHA, BigDecimal.ONE);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransacaoService service;

    @Test
    public void realizaTransacaoRetornaOk() throws Exception {

        var requestBody = new ObjectMapper().writeValueAsString(TRANSACAO_REQUEST);

        doNothing().when(service).execute(TRANSACAO_REQUEST);

        this.mockMvc.perform(post(URI)
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().string("OK"));

        verify(service, times(1)).execute(any(TransacaoDto.class));
    }

}
