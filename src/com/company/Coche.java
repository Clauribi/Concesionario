package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Coche {
    private String marca;
    private String modelo;
    private String matricula;
    private double precioCompra;
    private double precioVenta;
    private String tipo;
    private EstadoCoche estado;

    public EstadoCoche getEstado() {
        return estado;
    }

    public void setEstado(EstadoCoche estado) {
        cambiarCocheLista(this.matricula);
        this.estado = estado;
    }

    public String getMatricula() {
        return matricula;
    }

    private Exposicion exposicion;

    public boolean isReparando() {
        return reparando;
    }

    public boolean isReservado() {
        return reservado;
    }

    public boolean isEnVenta() {
        return enVenta;
    }

    public boolean isVendido() {
        return vendido;
    }

    private boolean reparando;
    private boolean reservado;
    private boolean enVenta;
    private boolean vendido;
    private HashMap<String, Reparacion> reparaciones;

    public HashMap<String, Reparacion> getReparaciones() {
        return reparaciones;
    }

    private HashMap<String, Coche> cochesReparacion;
    private HashMap<String, Coche> cochesVenta;
    private HashMap<String, Coche> cochesReservados;
    private HashMap<String, Coche> cochesVendidos;

    public HashMap<String, Coche> getCochesReparacion() {
        return cochesReparacion;
    }

    public HashMap<String, Coche> getCochesVenta() {
        return cochesVenta;
    }

    public HashMap<String, Coche> getCochesReservados() {
        return cochesReservados;
    }

    public HashMap<String, Coche> getCochesVendidos() {
        return cochesVendidos;
    }

    public Coche(String marca, String modelo, String matricula, double precioCompra, double precioVenta, String tipo, Exposicion exposicion) throws ExceptionParametrosInvalidos {
        if (marca == null) throw new ExceptionParametrosInvalidos("La marca no puede ser null.");
        this.marca = marca;
        if (modelo == null) throw new ExceptionParametrosInvalidos("El modelo no puede ser null.");
        this.modelo = modelo;
        if (matricula == null) throw new ExceptionParametrosInvalidos("La matrícula no puede ser null.");
        this.matricula = matricula;
        this.precioCompra = precioCompra;
        if (precioVenta <= precioCompra)
            throw new ExceptionParametrosInvalidos("El precio de venta no puede ser inferior o igual al precio de compra.");
        this.precioVenta = precioVenta;
        if (tipo != "turismo" || tipo != "industrial" || tipo != "todoterreno")
            throw new ExceptionParametrosInvalidos("Los tipos de vehículos permitidos son: 'turismo', 'industrial' o 'todoterreno'");
        this.tipo = tipo;
        if (exposicion == null) throw new ExceptionParametrosInvalidos("La exposicion no existe.");
        this.exposicion = exposicion;
        this.estado = EstadoCoche.enVenta;
        exposicion.addCoche(new Coche(marca, modelo, matricula, precioCompra, precioVenta, tipo, exposicion));
    }

    public void cambiarExposicion(Exposicion expo) throws ExceptionParametrosInvalidos {
        if (expo == null) throw new ExceptionParametrosInvalidos("La exposicion no existe.");
        this.exposicion = expo;
    }

    public void consultaReparaciones(String matricula) throws ExceptionParametrosInvalidos {
        if (this.reparaciones.containsKey(matricula)) {
            this.reparaciones.get(matricula);
        } else {
            throw new ExceptionParametrosInvalidos("La matricula no existe o no tiene reparaciones.");
        }
    }

    public void cambiarCocheLista(String matricula){
        if (estado == EstadoCoche.enVenta) {
            Coche c = exposicion.getListadoCoches().get(matricula);
            cochesVenta.put(matricula, c);
            if (cochesReparacion.containsKey(matricula)) cochesReparacion.remove(matricula);
            if (cochesReservados.containsKey(matricula)) cochesReservados.remove(matricula);
        }
        if (estado == EstadoCoche.reservado) {
            Coche c = exposicion.getListadoCoches().get(matricula);
            cochesReservados.put(matricula, c);
            if (cochesReparacion.containsKey(matricula)) cochesReparacion.remove(matricula);
            if (cochesVenta.containsKey(matricula)) cochesVenta.remove(matricula);
        }
        if (estado == EstadoCoche.reparando) {
            Coche c = exposicion.getListadoCoches().get(matricula);
            cochesReparacion.put(matricula, c);
            if (cochesVenta.containsKey(matricula)) cochesVenta.remove(matricula);
            if (cochesReservados.containsKey(matricula)) cochesReservados.remove(matricula);
        }
        if (estado == EstadoCoche.vendido) {
            Coche c = exposicion.getListadoCoches().get(matricula);
            cochesVendidos.put(matricula, c);
        }
    }


    public String getInfo() {
        return "Marca: " + this.marca +
                "\nModelo: " + this.modelo;
    }
}
