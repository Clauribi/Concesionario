package com.company;

import java.util.HashMap;

public class Cliente extends Persona {
    private HashMap<String, Coche> comprados = new HashMap<>();
    private HashMap<String, Coche> reservados = new HashMap<>();

    public HashMap<String, Coche> getComprados() {
        return comprados;
    }

    public HashMap<String, Coche> getReservados() {
        return reservados;
    }

    public Cliente(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        super(nombre, direccion, dni, telefono);
    }

    public void agregarCocheComprado(Coche coche) {
        this.comprados.put(coche.getMatricula(), coche);
    }

    public void agregarCocheReservado(Coche coche) {
        this.reservados.put(coche.getMatricula(), coche);
    }

}
