package br.com.vr.miniautorizador.database.repository;

import br.com.vr.miniautorizador.database.entity.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {
    Optional<CartaoEntity> findByNumeroCartao(String numeroCartao);
}
