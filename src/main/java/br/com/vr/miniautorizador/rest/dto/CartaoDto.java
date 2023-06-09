package br.com.vr.miniautorizador.rest.dto;

import br.com.vr.miniautorizador.database.entity.CartaoEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CartaoDto {

    @NotNull
    private String numeroCartao;
    @NotNull
    private String senha;

    public CartaoDto(CartaoEntity entity) {
        this.numeroCartao = entity.getNumeroCartao();
        this.senha = entity.getSenha();
    }
}
