package ru.dakon.MoexService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler({BondNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleNotFound(Exception ex) {
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BondParsingException.class})
    public ResponseEntity<ErrorDTO> handleExceptionFromPriceService(Exception ex) {
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({LimitRequestsException.class})
    public ResponseEntity<ErrorDTO> handleExceptionFromMoex(Exception ex) {
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getLocalizedMessage()), HttpStatus.TOO_MANY_REQUESTS);
    }
}
