package ru.miroshka.market.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public  ResourceNotFoundException (String message){
        super(message);
    }
}
