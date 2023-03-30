package br.com.vr.miniautorizador.service;

import br.com.vr.miniautorizador.database.entity.CartaoEntity;
import br.com.vr.miniautorizador.database.repository.CartaoRepository;
import br.com.vr.miniautorizador.exception.CartaoNaoEncontradoExcetion;
import br.com.vr.miniautorizador.rest.dto.CartaoDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CartaoService {

    private CartaoRepository repository;

    public ResponseEntity<CartaoDto> register(CartaoDto request) {

        var findEntity = repository.findByNumeroCartao(request.getNumeroCartao());

        return findEntity.map(cartaoEntity -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(new CartaoDto(cartaoEntity)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new CartaoDto(repository.save(new CartaoEntity(request)))));
    }

    public BigDecimal find(String numeroCartao) {
        var findEntity = repository.findByNumeroCartao(numeroCartao)
                .orElseThrow(CartaoNaoEncontradoExcetion::new);

        return findEntity.getSaldo();
    }
}
