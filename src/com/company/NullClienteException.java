package com.company;

public class NullClienteException extends NullPointerException{
    public NullClienteException(){
        super ("** NO EXISTE EL CLIENTE **");
    }
}
