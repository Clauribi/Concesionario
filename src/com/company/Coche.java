package com.company;

public class Coche {
    private String marca;
    private String modelo;
    private String matricula;
    private double precioCompra;
    private double precioVenta;
    private String tipo;

    private Exposicion exposicion;

    private boolean reservado;
    private boolean comprado;
    private boolean vendido;

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
    }
}
