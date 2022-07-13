package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Cliente extends Persona {
    private ArrayList<Coche> comprados = new ArrayList<>();
    private ArrayList<Coche> reservados = new ArrayList<>();

    public ArrayList<Coche> getComprados() {
        return comprados;
    }

    public ArrayList<Coche> getReservados() {
        return reservados;
    }

    public Cliente(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        super(nombre, direccion, dni, telefono);
    }

    public void agregarCocheComprado(Coche coche) {
        this.comprados.add(coche);
    }

    public void agregarCocheReservado(Coche coche) {
        this.reservados.add(coche);
    }

}
