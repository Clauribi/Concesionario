package com.company;

public class Exposicion {
    private int numExposicion;
    private String direccion;
    private int telefono;

    public Exposicion(int numExposicion, String direccion, int telefono) throws ExceptionParametrosInvalidos {
        if (numExposicion == 0) throw new ExceptionParametrosInvalidos("El número de la exposición no puede ser 0.");
        this.numExposicion = numExposicion;
        if (direccion == null) throw new ExceptionParametrosInvalidos("La dirección no puede ser null.");
        this.direccion = direccion;
        if (Integer.toString(telefono).length() != 9) throw new ExceptionParametrosInvalidos("El teléfono tiene que tener 9 dígitos.");
        this.telefono = telefono;
    }
}
