package br.com.vr.miniautorizador.rest.controller;

import br.com.vr.miniautorizador.rest.dto.CartaoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cartoes")
@Slf4j
public class CartaoController {

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<BigDecimal> get(@PathVariable("numeroCartao") String numeroCartao) {
        log.info("Consultando saldo do cartão: {}", numeroCartao);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BigDecimal.ZERO);
    }

    @PostMapping
    public ResponseEntity<CartaoDto> post(@RequestBody CartaoDto request) {
        log.info("Criação do cartão: {}", request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CartaoDto());
    }


}
