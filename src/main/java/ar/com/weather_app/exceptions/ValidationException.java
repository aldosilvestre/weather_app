package ar.com.weather_app.exceptions;

public class ValidationException extends RuntimeException{

    public ValidationException(String message, Throwable error){
        super(message, error);
    }

}
