package br.com.vr.miniautorizador.rest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartaoDto {

    private String numeroCartao;
    private String senha;
}
