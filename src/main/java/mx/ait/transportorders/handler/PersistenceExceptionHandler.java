package mx.ait.transportorders.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import mx.ait.transportorders.dto.response.ErrorResponse;

@RestControllerAdvice
public class PersistenceExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(DataIntegrityViolationException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Unprocessable Entity",
                "Ya se encuentra el registro"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
