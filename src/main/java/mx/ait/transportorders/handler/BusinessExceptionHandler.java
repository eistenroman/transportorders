package mx.ait.transportorders.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import mx.ait.transportorders.dto.response.ErrorResponse;
import mx.ait.transportorders.exception.OrdersException;
import mx.ait.transportorders.exception.StatusOrdersException;

@RestControllerAdvice
public class BusinessExceptionHandler extends PersistenceExceptionHandler {

	@ExceptionHandler(StatusOrdersException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(StatusOrdersException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Unprocessable Entity",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(OrdersException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(OrdersException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Unprocessable Entity",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
