package com.company;

import java.util.ArrayList;


public class Coche {
    private String marca;
    private String modelo;
    private String matricula;
    private double precioCompra;
    private double precioVenta;
    private String tipo;

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
    private ArrayList<Reparacion> reparaciones;

    public ArrayList<Reparacion> getReparaciones() {
        return reparaciones;
    }

    private ArrayList<Coche> cochesReparacion;
    private ArrayList<Coche> cochesVenta;
    private ArrayList<Coche> cochesReservados;
    private ArrayList<Coche> cochesVendidos;


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
        this.enVenta =true;
    }
    public void cambiarExposicion(Exposicion expo) throws ExceptionParametrosInvalidos {
        if (expo==null) throw new ExceptionParametrosInvalidos("La exposicion no existe.");
        this.exposicion= expo;
    }
    public void estado (Boolean estado) {
        if (estado = reparando) {
            reservado = false;
            enVenta = false;
            vendido = false;
        } else if (estado = reservado) {
            reparando = false;
            enVenta = false;
            vendido = false;
        } else if (estado = enVenta) {
            reparando = false;
            reservado = false;
            vendido = false;
        } else if (estado = vendido) {
            reservado = false;
            reparando = false;
            enVenta = false;
        }
    }
}
