package com.company;

public class NullExposicionException extends NullPointerException{
    public NullExposicionException(){
        super ("** NO EXISTE LA EXPOSICIÓN **");
    }
}
