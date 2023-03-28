package br.com.vr.miniautorizador.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransacaoDto {

    private String numeroCartao;
    private String senha;
    private BigDecimal valor;
}
