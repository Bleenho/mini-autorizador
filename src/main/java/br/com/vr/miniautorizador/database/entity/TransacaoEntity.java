package br.com.vr.miniautorizador.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CartaoEntity cartao;

    private LocalDateTime inicio;

    private LocalDateTime fim;

    private String erro;

    public TransacaoEntity(Long idCartao){
        this.cartao = new CartaoEntity(idCartao);
        this.inicio = LocalDateTime.now();
    }
}

