package ar.com.weather_app.exceptions;

public class DomainException extends RuntimeException{

    public DomainException(String message){
        super(message);
    }
}
