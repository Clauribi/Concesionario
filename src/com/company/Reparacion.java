package com.company;


import java.time.LocalDateTime;

public class Reparacion implements Comparable<Reparacion> {
    private TipoReparacion tipo;
    private Coche coche;
    private boolean resuelta;
    private LocalDateTime fechaDeCreacion;

    public Reparacion(TipoReparacion tipo, Coche coche) throws ExceptionParametrosInvalidos {
        if (tipo == null)
            throw new ExceptionParametrosInvalidos("Los tipos de reparación tienen que ser: 'mecanica', 'electrica', 'chapa' o 'revision'");
        this.tipo = tipo;
        if (coche == null) throw new ExceptionParametrosInvalidos("El coche no existe.");
        this.coche = coche;
        this.coche.setEstado(EstadoCoche.reparando);
        this.resuelta = false;
        this.fechaDeCreacion = LocalDateTime.now();
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public boolean isResuelta() {
        return resuelta;
    }

    public void resolver(Coche coche) {
        coche.setEstado(EstadoCoche.enVenta);
        this.resuelta = true;
    }

    public String getInfo() {
        return "Tipo de reparación: " + tipo +
                "\nFecha: " + this.fechaDeCreacion;
    }

    @Override
    public int compareTo(Reparacion o) {
        return o.getFechaDeCreacion().compareTo(this.fechaDeCreacion) * 1;
    }
}
