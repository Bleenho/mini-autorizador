package br.com.vr.miniautorizador.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransacaoDto {

    @NotNull
    private String numeroCartao;
    @NotNull
    private String senha;
    @NotNull
    private BigDecimal valor;
}
