package com.company;

import java.util.HashMap;

public class Coche {
    private String marca;
    private String modelo;
    private String matricula;
    private double precioCompra;
    private double precioVenta;
    private TipoCoche tipo;
    private EstadoCoche estado;
    private Exposicion exposicion;
    private HashMap<String, Coche> listadoCoches = new HashMap<>();
    private HashMap<String, Coche> cochesReparacion;
    private HashMap<String, Coche> cochesVenta;
    private HashMap<String, Coche> cochesReservados;
    private HashMap<String, Coche> cochesVendidos;

    private HashMap<String, Reparacion> reparaciones;

    public void setExposicion(Integer numExpo) {
        if(exposicion.getListasExpos().containsKey(numExpo)){
         this.exposicion= exposicion.getListasExpos().get(numExpo);
        }

    }
    public EstadoCoche getEstado() {
        return estado;
    }

    public void setEstado(EstadoCoche estado) throws ExceptionParametrosInvalidos {
        cambiarCocheLista(this.matricula);
        this.estado = estado;
    }

    public String getMatricula() {
        return matricula;
    }

    public HashMap<String, Coche> getListadoCoches() {
        return listadoCoches;
    }

    public HashMap<String, Reparacion> getReparaciones() {
        return reparaciones;
    }

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

    public Coche(String marca, String modelo, String matricula, double precioCompra, double precioVenta, TipoCoche tipo) throws ExceptionParametrosInvalidos {
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
        if (tipo == null)
            throw new ExceptionParametrosInvalidos("Los tipos de vehículos permitidos son: 'turismo', 'industrial' o 'todoterreno'");
        this.tipo = tipo;
//        if (exposicion == null) throw new ExceptionParametrosInvalidos("La exposición no existe.");
//        this.exposicion = exposicion;
        this.estado = EstadoCoche.enVenta;
        this.listadoCoches.put(matricula,new Coche(marca, modelo, matricula, precioCompra, precioVenta, tipo));
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

    public void cambiarCocheLista(String matricula) throws ExceptionParametrosInvalidos {
        if (!listadoCoches.containsKey(matricula))
            throw new ExceptionParametrosInvalidos("El coche no está en ningún listado.");
        if (cochesVendidos.containsKey(matricula))
            throw new ExceptionParametrosInvalidos("El coche que deseas cambiar está vendido.");
        if (estado == EstadoCoche.enVenta) {
            Coche c = listadoCoches.get(matricula);
            cochesVenta.put(matricula, c);
            if (cochesReparacion.containsKey(matricula)) cochesReparacion.remove(matricula);
            if (cochesReservados.containsKey(matricula)) cochesReservados.remove(matricula);
        }
        if (estado == EstadoCoche.reservado) {
            Coche c = listadoCoches.get(matricula);
            cochesReservados.put(matricula, c);
            if (cochesReparacion.containsKey(matricula)) cochesReparacion.remove(matricula);
            if (cochesVenta.containsKey(matricula)) cochesVenta.remove(matricula);
        }
        if (estado == EstadoCoche.reparando) {
            Coche c = listadoCoches.get(matricula);
            cochesReparacion.put(matricula, c);
            if (cochesVenta.containsKey(matricula)) cochesVenta.remove(matricula);
            if (cochesReservados.containsKey(matricula)) cochesReservados.remove(matricula);
        }
        if (estado == EstadoCoche.vendido) {
            Coche c = listadoCoches.get(matricula);
            cochesVendidos.put(matricula, c);
            if (cochesVenta.containsKey(matricula)) cochesVenta.remove(matricula);
            if (cochesReservados.containsKey(matricula)) cochesReservados.remove(matricula);
            if (cochesReparacion.containsKey(matricula)) cochesReparacion.remove(matricula);
        }
    }

}
