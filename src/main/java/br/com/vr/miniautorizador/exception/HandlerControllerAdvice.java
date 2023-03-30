package br.com.vr.miniautorizador.exception;


import br.com.vr.miniautorizador.exception.constants.TransacaoExceptionEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CartaoNaoEncontradoExcetion.class)
    public ResponseEntity<Void> handleCartaoNaoEncontradoExcetion() {
        return ResponseEntity
                .notFound().build();
    }

    @ExceptionHandler(TransacaoException.class)
    public ResponseEntity<TransacaoExceptionEnum> handleTransacaoException(TransacaoException ex) {
        return ResponseEntity
                .unprocessableEntity()
                .body(ex.getRegra());
    }
}
