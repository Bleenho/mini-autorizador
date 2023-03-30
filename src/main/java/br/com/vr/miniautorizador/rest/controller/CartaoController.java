package br.com.vr.miniautorizador.rest.controller;

import br.com.vr.miniautorizador.rest.dto.CartaoDto;
import br.com.vr.miniautorizador.service.CartaoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cartoes")
@Slf4j
@AllArgsConstructor
public class CartaoController {

    private CartaoService service;

    @PostMapping
    public ResponseEntity<CartaoDto> post(@RequestBody CartaoDto request) {
        log.info("Criação do cartão: {}", request);
        return service.register(request);
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<BigDecimal> get(@PathVariable("numeroCartao") String numeroCartao) {
        log.info("Consultando saldo do cartão: {}", numeroCartao);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.find(numeroCartao));
    }
}
