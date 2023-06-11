package com.lax.user.service.exception;

public class ResourceNotFoundException extends RuntimeException{

    //we can manage this class wrt our requirement
    public ResourceNotFoundException(){
        super("Resource not Found on Server !!");
    }

    public  ResourceNotFoundException(String message){
        super(message);
    }
}
