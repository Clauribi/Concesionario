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
            cliente.agregarCocheComprado(coche);
        }else if (coche.getEstado() == EstadoCoche.reservado){
            if (coche.getCliente() == cliente) {
                coche.setEstado(EstadoCoche.vendido);
                cochesVendidos.put(coche.getMatricula(), cliente);
                cliente.agregarCocheComprado(coche);
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
            cliente.agregarCocheReservado(coche);
        } else {
            throw new ExceptionParametrosInvalidos("El coche no se puede reservar porque el estado es " + coche.getEstado());
        }
    }

    public void cancelarReserva (Coche coche, Cliente cliente) throws ExceptionParametrosInvalidos {
        if (coche.getEstado() == EstadoCoche.reservado){
            coche.setEstado(EstadoCoche.enVenta);
        } else {
            throw new ExceptionParametrosInvalidos("No se puede cancelar la reserva del coche porque el estado es: " + coche.getEstado());
        }
    }
}
