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
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    private HashMap<String, Reparacion> reparaciones;

    public Exposicion getExposicion() {
        return exposicion;
    }

    public void setEstado(EstadoCoche estado) {
        this.estado = estado;
    }

    public EstadoCoche getEstado() {
        return estado;
    }

    public String getMatricula() {
        return matricula;
    }

    public HashMap<String, Reparacion> getReparaciones() {
        return reparaciones;
    }

    public void setExposicion(Exposicion exposicion) {
        this.exposicion = exposicion;
    }

    public Coche(String marca, String modelo, String matricula, double precioCompra, double precioVenta, TipoCoche tipo, Exposicion exposicion) throws ExceptionParametrosInvalidos {
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
        if (exposicion == null) throw new ExceptionParametrosInvalidos("La exposición no existe.");
        this.exposicion = exposicion;
        this.estado = EstadoCoche.enVenta;
    }

    public void cambiarExposicion(Exposicion expo) throws ExceptionParametrosInvalidos {
        if (expo == null) throw new ExceptionParametrosInvalidos("La exposicion no existe.");
        setExposicion(expo);
    }

    public void consultaReparaciones(String matricula) throws ExceptionParametrosInvalidos {
        if (this.reparaciones.containsKey(matricula)) {
            this.reparaciones.get(matricula);
        } else {
            throw new ExceptionParametrosInvalidos("La matricula no existe o no tiene reparaciones.");
        }
    }

    public String getInfo() {
        return "\nMatrícula: " + this.matricula +
                "\nMarca: " + this.marca +
                "\nModelo: " + this.modelo +
                "\nTipo: " + this.tipo +
                "\nPrecio: " + this.precioVenta +
                "\nEstado: " + this.estado +
                "\nNúmero exposición: " + this.exposicion.getNumExposicion() + "\n";
    }
}
