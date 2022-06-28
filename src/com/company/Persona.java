package com.company;

public class Persona {
    private String nombre;
    private String direccion;
    private String dni;
    private int telefono;

    public Persona(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        if(nombre==null) throw new ExceptionParametrosInvalidos("El nombre no puede ser null.");
        this.nombre = nombre;
        if(direccion==null) throw new ExceptionParametrosInvalidos("La dirección no puede ser null.");
        this.direccion = direccion;
        if(dni==null) throw new ExceptionParametrosInvalidos("El dni no puede ser null.");
        this.dni = dni;
        if(telefono<=0) throw new ExceptionParametrosInvalidos("El telefono no puede ser 0 o menor de 0");
        this.telefono = telefono;
    }
}
