package br.com.vr.miniautorizador.database.entity;

import br.com.vr.miniautorizador.rest.dto.CartaoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_cartao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoEntity {

    private static final BigDecimal SALDO_INICIAL = BigDecimal.valueOf(500);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroCartao;

    private String senha;

    private BigDecimal saldo;

    public CartaoEntity(CartaoDto request) {
        this.numeroCartao = request.getNumeroCartao();
        this.senha = request.getSenha();
        this.saldo = SALDO_INICIAL;
    }
}
