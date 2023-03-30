package br.com.vr.miniautorizador.database.repository;

import br.com.vr.miniautorizador.database.entity.CartaoEntity;
import br.com.vr.miniautorizador.database.repository.CartaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static br.com.vr.miniautorizador.util.Constants.NUMERO_CARTAO;
import static br.com.vr.miniautorizador.util.Constants.SENHA;
import static java.math.BigDecimal.ONE;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartaoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CartaoRepository repository;

    @Test
    void quandoConsultaCartaoPeloNumero() {
        this.entityManager.persist(new CartaoEntity(null, NUMERO_CARTAO, SENHA, ONE));
        var cartao = this.repository.findByNumeroCartao(NUMERO_CARTAO);
        assertThat(cartao.isPresent()).isEqualTo(true);
        assertThat(cartao.get().getNumeroCartao()).isEqualTo(NUMERO_CARTAO);
        assertThat(cartao.get().getSenha()).isEqualTo(SENHA);
    }
}
