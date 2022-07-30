package com.company;

public class DuplicadoException extends Exception{
    public DuplicadoException(){
        super ("** ELEMENTO EXISTENTE. CANCELANDO OPERACIÓN... **");
    }
}
