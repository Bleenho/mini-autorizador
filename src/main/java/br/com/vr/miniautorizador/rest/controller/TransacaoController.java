package br.com.vr.miniautorizador.rest.controller;

import br.com.vr.miniautorizador.rest.dto.TransacaoDto;
import br.com.vr.miniautorizador.service.TransacaoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
@Slf4j
@AllArgsConstructor
public class TransacaoController {

    private TransacaoService service;

    @PostMapping
    public ResponseEntity<String> post(@RequestBody TransacaoDto request) {
        log.info("Realizando Transação: {}", request);

        service.execute(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("OK");
    }
}
