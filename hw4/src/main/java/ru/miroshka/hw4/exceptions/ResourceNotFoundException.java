package ru.miroshka.hw4.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public  ResourceNotFoundException (String message){
        super(message);
    }
}
