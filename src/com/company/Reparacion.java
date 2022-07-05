package com.company;

public class Reparacion {
    private TipoReparacion tipo;
    private Coche coche;
    private boolean resuelta;

    public Reparacion(TipoReparacion tipo, Coche coche) throws ExceptionParametrosInvalidos {
        if (tipo == null)
            throw new ExceptionParametrosInvalidos("Los tipos de reparación tienen que ser: 'mecanica', 'electrica', 'chapa' o 'revision'");
        this.tipo = tipo;
        if (coche == null) throw new ExceptionParametrosInvalidos("El coche no existe.");
        this.coche = coche;
        this.coche.setEstado(EstadoCoche.reparando);
    }

    public void resolver() throws ExceptionParametrosInvalidos {
        this.coche.setEstado(EstadoCoche.enVenta);
        coche.getReparaciones().put(this.coche.getMatricula(), new Reparacion(this.tipo, this.coche));
    }

}
