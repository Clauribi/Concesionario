package com.company;

import java.util.ArrayList;

public class Exposicion {
    private int numExposicion;
    private String direccion;
    private int telefono;
    private ArrayList<Coche> listadoCoches = new ArrayList<>();

    public Exposicion(int numExposicion, String direccion, int telefono) throws ExceptionParametrosInvalidos {
        if (numExposicion == 0) throw new ExceptionParametrosInvalidos("El número de la exposición no puede ser 0.");
        this.numExposicion = numExposicion;
        if (direccion == null) throw new ExceptionParametrosInvalidos("La dirección no puede ser null.");
        this.direccion = direccion;
        if (Integer.toString(telefono).length() != 9)
            throw new ExceptionParametrosInvalidos("El teléfono tiene que tener 9 dígitos.");
        this.telefono = telefono;
    }

    public void addCoche(Coche coche) throws ExceptionParametrosInvalidos {
        if (coche == null) throw new ExceptionParametrosInvalidos("El coche no existe.");
        this.listadoCoches.add(coche);
    }

    public void borrarCoche(Coche coche) throws ExceptionParametrosInvalidos {
        if (!this.listadoCoches.contains(coche))
            throw new ExceptionParametrosInvalidos("El coche no esta en la posicion indicada.");
        this.listadoCoches.remove(coche);
    }
}
