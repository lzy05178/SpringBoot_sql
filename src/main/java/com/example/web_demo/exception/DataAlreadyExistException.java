package com.example.web_demo.exception;

public class DataAlreadyExistException extends RuntimeException{
    public DataAlreadyExistException(){
        super();
    }
    public DataAlreadyExistException(String message){
        super(message);
    }
}
