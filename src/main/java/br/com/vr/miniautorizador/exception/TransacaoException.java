package br.com.vr.miniautorizador.exception;

import br.com.vr.miniautorizador.exception.constants.TransacaoExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TransacaoException extends RuntimeException {

    @Getter
    private TransacaoExceptionEnum regra;
}
