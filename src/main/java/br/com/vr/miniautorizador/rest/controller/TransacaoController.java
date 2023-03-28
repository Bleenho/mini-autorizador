package br.com.vr.miniautorizador.rest.controller;

import br.com.vr.miniautorizador.rest.dto.TransacaoDto;
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
public class TransacaoController {

    @PostMapping
    public ResponseEntity<String> post(@RequestBody TransacaoDto request) {
        log.info("Realizando Transação: {}", request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("OK");
    }
}
