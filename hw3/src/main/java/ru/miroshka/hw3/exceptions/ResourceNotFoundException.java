package ru.miroshka.hw3.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public  ResourceNotFoundException (String message){
        super(message);
    }
}
