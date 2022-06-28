package com.company;

public class Cliente extends Persona {

    public Cliente(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        super(nombre, direccion, dni, telefono);
    }
}
