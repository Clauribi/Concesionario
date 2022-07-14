package com.company;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Reparacion implements Comparable<Reparacion> {
    private TipoReparacion tipo;
    private Coche coche;
    private boolean resuelta;
    private Long fechaDeCreacion;

    public Reparacion(TipoReparacion tipo, Coche coche) throws ExceptionParametrosInvalidos {
        if (tipo == null)
            throw new ExceptionParametrosInvalidos("Los tipos de reparación tienen que ser: 'mecanica', 'electrica', 'chapa' o 'revision'");
        this.tipo = tipo;
        if (coche == null) throw new ExceptionParametrosInvalidos("El coche no existe.");
        this.coche = coche;
        this.coche.setEstado(EstadoCoche.reparando);
        this.resuelta = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.setFechaDeCreacion(Long.parseLong(dtf.format(LocalDateTime.now())));
    }

    public void setFechaDeCreacion(Long fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public boolean isResuelta() {
        return resuelta;
    }

    public void resolver() throws ExceptionParametrosInvalidos {
        this.coche.setEstado(EstadoCoche.enVenta);
        this.resuelta = true;
    }

    public String getInfo(){
        return "Tipo de reparación: " + tipo +
                "Fecha: " + this.fechaDeCreacion;
    }

    @Override
    public int compareTo(Reparacion o) {
        if (this.fechaDeCreacion > o.fechaDeCreacion) return -1;
        if (this.fechaDeCreacion<o.fechaDeCreacion) return 1;
        return 0;
    }
}
