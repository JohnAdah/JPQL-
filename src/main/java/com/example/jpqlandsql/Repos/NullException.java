package com.example.jpqlandsql.Repos;

import org.springframework.stereotype.Component;

@Component
public class NullException extends Exception{

    private String message;

    public NullException(String msg){
        this.message = msg;
    }

    public NullException(){

    }

    @Override
    public String toString() {
        return "NullException{" +
                "message='" + message + '\'' +
                '}';
    }
}
