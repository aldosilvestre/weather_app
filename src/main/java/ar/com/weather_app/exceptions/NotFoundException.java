package ar.com.weather_app.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super("No se encontro el recurso");
    }
}
