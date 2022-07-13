package com.company;

import java.util.HashMap;

public class VendedorComision extends Persona {
    private HashMap<String, Cliente> cochesVendidos = new HashMap<>();

    public HashMap<String, Cliente> getCochesVendidos() {
        return cochesVendidos;
    }

    public VendedorComision(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        super(nombre, direccion, dni, telefono);
    }

    public void venderCoche(Coche coche, Cliente cliente) throws ExceptionParametrosInvalidos {
        if (coche.getEstado() == EstadoCoche.enVenta) {
            coche.setEstado(EstadoCoche.vendido);
            cochesVendidos.put(coche.getMatricula(), cliente);
        }else if (coche.getEstado() == EstadoCoche.reservado){
            if (coche.getCliente() == cliente) {
                coche.setEstado(EstadoCoche.vendido);
                cochesVendidos.put(coche.getMatricula(), cliente);
            } else {
                throw new ExceptionParametrosInvalidos("El coche con matricula " + coche.getMatricula() + " está reservado por " + coche.getCliente());
            }
        } else {
            throw new ExceptionParametrosInvalidos("El coche no se puede vender porque el estado es " + coche.getEstado());
        }
    }

    public void reservarCoche(Coche coche, Cliente cliente) throws ExceptionParametrosInvalidos {
        if (coche.getEstado() == EstadoCoche.enVenta){
            coche.setEstado(EstadoCoche.reservado);
        } else {
            throw new ExceptionParametrosInvalidos("El coche no se puede reservar porque el estado es " + coche.getEstado());
        }
    }
}
