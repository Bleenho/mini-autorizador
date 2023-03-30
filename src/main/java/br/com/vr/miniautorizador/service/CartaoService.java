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

    public ResponseEntity<CartaoDto> cadastrar(CartaoDto request) {

        var findEntity = repository.findByNumeroCartao(request.getNumeroCartao());

        return findEntity.map(cartaoEntity -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(new CartaoDto(cartaoEntity)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new CartaoDto(repository.save(new CartaoEntity(request)))));
    }

    public BigDecimal getSaldo(String numeroCartao) {
        var findEntity = find(numeroCartao);

        return findEntity.getSaldo();
    }

    public CartaoEntity find(String numeroCartao) {
        return repository.findByNumeroCartao(numeroCartao)
                .orElseThrow(CartaoNaoEncontradoExcetion::new);

    }

    public void save(CartaoEntity entity) {
        repository.save(entity);
    }
}
