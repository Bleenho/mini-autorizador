package br.com.vr.miniautorizador.database.repository;

import br.com.vr.miniautorizador.database.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {

    List<TransacaoEntity> findByCartaoIdAndFimIsNull(Long id);
}
