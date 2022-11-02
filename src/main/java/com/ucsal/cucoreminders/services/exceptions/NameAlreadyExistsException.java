package com.ucsal.cucoreminders.services.exceptions;

public class NameAlreadyExistsException extends RuntimeException{

    public NameAlreadyExistsException(String msg){
        super(msg);
    }
}
