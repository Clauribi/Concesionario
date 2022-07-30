package com.company;

public class NullVendedorException extends NullPointerException{
    public NullVendedorException(){
        super("** NO EXISTE EL VENDEDOR **");
    }
}
