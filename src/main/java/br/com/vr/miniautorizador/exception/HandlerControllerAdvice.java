package br.com.vr.miniautorizador.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class HandlerControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CartaoNaoEncontradoExcetion.class)
    public ResponseEntity<Void> handleFraudeException() {
        log.info("Cartão nao encontrado");
        return ResponseEntity
                .notFound().build();
    }

}
