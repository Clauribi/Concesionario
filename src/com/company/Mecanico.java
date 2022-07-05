package com.company;

import java.util.HashMap;

public class Mecanico extends Persona{
    private HashMap<String, Reparacion> reparaciones;
    private Coche coche;

    public HashMap<String, Reparacion> getReparaciones() {
        return reparaciones;
    }

    public Mecanico(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        super(nombre, direccion, dni, telefono);
    }

    public void consultaReparaciones(String matricula) throws ExceptionParametrosInvalidos {
        if (this.reparaciones.containsKey(matricula)) {
            this.reparaciones.get(matricula);
        } else {
            throw new ExceptionParametrosInvalidos("La matricula no existe o no tiene reparaciones.");
        }
    }

    public void repararCoche(TipoReparacion tipo, String matricula) throws ExceptionParametrosInvalidos {
//        coche = coche.getListadoCoches().get(matricula);
        Reparacion r = new Reparacion(tipo, coche);
    }
}
