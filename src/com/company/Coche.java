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
    private HashMap<String, Reparacion> reparaciones;

    public void setMarca(String marca) throws ExceptionParametrosInvalidos {
        if (marca == null) throw new ExceptionParametrosInvalidos("La marca no puede ser null.");
        this.marca = marca;
    }

    public void setModelo(String modelo) throws ExceptionParametrosInvalidos {
        if (modelo == null) throw new ExceptionParametrosInvalidos("El modelo no puede ser null.");
        this.modelo = modelo;
    }

    public void setMatricula(String matricula) throws ExceptionParametrosInvalidos {
        if (matricula == null) throw new ExceptionParametrosInvalidos("La matrícula no puede ser null.");
        this.matricula = matricula;
    }

    public void setPrecioCompra(double precioCompra) throws ExceptionParametrosInvalidos {
        if (precioCompra < 0) throw new ExceptionParametrosInvalidos("El precio de compra no puede ser inferior a 0.");
        this.precioCompra = precioCompra;
    }

    public void setPrecioVenta(double precioVenta) throws ExceptionParametrosInvalidos {
        if (precioVenta <= precioCompra)
            throw new ExceptionParametrosInvalidos("El precio de venta no puede ser inferior o igual al precio de compra.");
        this.precioVenta = precioVenta;
    }

    public void setTipo(TipoCoche tipo) throws ExceptionParametrosInvalidos {
        if (tipo == null)
            throw new ExceptionParametrosInvalidos("Los tipos de vehículos permitidos son: 'turismo', 'industrial' o 'todoterreno'");
        this.tipo = tipo;
    }

    public void setExposicion(Exposicion exposicion) throws ExceptionParametrosInvalidos {
        if (exposicion == null) throw new ExceptionParametrosInvalidos("La exposición no existe.");
        this.exposicion = exposicion;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

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

    public Coche(String marca, String modelo, String matricula, double precioCompra, double precioVenta, TipoCoche tipo, Exposicion exposicion) throws ExceptionParametrosInvalidos {
        setMarca(marca);
        setModelo(modelo);
        setMatricula(matricula);
        setPrecioCompra(precioCompra);
        setPrecioVenta(precioVenta);
        setTipo(tipo);
        setExposicion(exposicion);
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

    public void updateInfo(String marca, String modelo, double precioCompra, double precioVenta, TipoCoche t, Exposicion exposicion) throws ExceptionParametrosInvalidos {
        setMarca(marca);
        setModelo(modelo);
        setPrecioCompra(precioCompra);
        setPrecioVenta(precioVenta);
        setTipo(t);
        setExposicion(exposicion);
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
