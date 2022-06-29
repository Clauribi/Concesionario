package com.company;

import java.util.ArrayList;

public class Cliente extends Persona {
    private ArrayList<Coche> comprados = new ArrayList<>();
    private ArrayList<Coche> reservados = new ArrayList<>();

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
