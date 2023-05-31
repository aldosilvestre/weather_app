package ar.com.weather_app.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class HandlerException {

    private final static Logger logger = LoggerFactory.getLogger(HandlerException.class);

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleHttpServerExceptions(HttpServerErrorException httpException){
        logger.error(httpException.getLocalizedMessage(), httpException);
        return new ResponseEntity<String>(httpException.getStatusText(), httpException.getStatusCode());
    }
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientExceptions(HttpClientErrorException httpException){
        logger.error(httpException.getLocalizedMessage(), httpException);
        return new ResponseEntity<String>(httpException.getLocalizedMessage(), httpException.getStatusCode());
    }

    @ExceptionHandler({ValidationException.class, DomainException.class})
    public ResponseEntity<String> handleValidationException(RuntimeException exception){
        logger.error(exception.getLocalizedMessage());
        return new ResponseEntity<String>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerUncontrollerExceptions(Exception e){
        logger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<String>("Ocurrio un error inesperado", HttpStatus.BAD_REQUEST);
    }
}
