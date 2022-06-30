package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class VendedorComision extends Persona {
    private HashMap<String, Coche> cochesVendidos = new HashMap<>();
    private ArrayList<Coche> listadoCoches;
    private Coche coche;

    public VendedorComision(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        super(nombre, direccion, dni, telefono);
    }

    public void agregarCocheVendido(String matricula) throws ExceptionParametrosInvalidos {
        if (coche.getCochesReservados().containsKey(matricula)) {
            coche = coche.getCochesReservados().get(matricula);
            this.cochesVendidos.put(matricula, coche);
        } else if (coche.getCochesVenta().containsKey(matricula)) {
            coche = coche.getCochesVenta().get(matricula);
            this.cochesVendidos.put(matricula, coche);
        } else {
            throw new ExceptionParametrosInvalidos("El coche no está en venta ni reservado.");
        }
    }

    public void venderCoche(String matricula) throws ExceptionParametrosInvalidos {
        if (coche.getCochesReservados().containsKey(matricula) || coche.getCochesVenta().containsKey(matricula)) {
            coche.setEstado(EstadoCoche.vendido);
            agregarCocheVendido(matricula);
        } else {
            throw new ExceptionParametrosInvalidos("El coche no está en venta ni reservado.");
        }
    }
}
