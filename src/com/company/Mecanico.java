package com.company;

import java.util.HashMap;

public class Mecanico extends Persona{
    private Coche coche;

    public Mecanico(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        super(nombre, direccion, dni, telefono);
    }

    public void repararCoche(TipoReparacion tipo, Coche coche) throws ExceptionParametrosInvalidos {
        Reparacion r = new Reparacion(tipo, coche);
        coche.getReparaciones().add(r);
    }
}
