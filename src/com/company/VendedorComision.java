package com.company;

import java.util.ArrayList;

public class VendedorComision extends Persona {
    private ArrayList<Coche> cochesVendidos = new ArrayList<>();
    private ArrayList<Coche> listadoCoches;

    public VendedorComision(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        super(nombre, direccion, dni, telefono);
    }
    public void agregarCocheVendido(Coche coche){
        this.cochesVendidos.add(coche);
    }

    public void venderCoche (String matricula) throws ExceptionParametrosInvalidos {
        Coche coche = coche.getCochesVenta().containsKey(matricula);
        if (coche.isEnVenta() || coche.isReservado()){
            coche.estado(coche.isVendido());
            agregarCocheVendido(coche);
        } else {
            throw new ExceptionParametrosInvalidos("El coche no está en venta ni reservado.");
        }
    }
}
