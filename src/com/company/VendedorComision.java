package com.company;

import java.util.ArrayList;

public class VendedorComision extends Persona {
    private ArrayList<Coche> cochesVendidos = new ArrayList<>();

    public VendedorComision(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        super(nombre, direccion, dni, telefono);
    }
    public void agregarCocheVendido(Coche coche){
        this.cochesVendidos.add(coche);

    }
}
