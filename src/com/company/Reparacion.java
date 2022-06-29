package com.company;

public class Reparacion {
    private String tipo;
    private Coche coche;
    private boolean resuelta;

    public Reparacion(String tipo, Coche coche) throws ExceptionParametrosInvalidos {
        if (tipo != "mecanica" || tipo != "electrica" || tipo != "chapa" || tipo != "revision")
            throw new ExceptionParametrosInvalidos("Los tipos de reparación tienen que ser: 'mecanica', 'electrica', 'chapa' o 'revision'");
        this.tipo = tipo;
        if (coche ==null) throw new ExceptionParametrosInvalidos("El coche no existe.");
        this.coche = coche;
        this.coche.estado(coche.isReparando());
    }
    public void resolver() throws ExceptionParametrosInvalidos {
        this.coche.estado(coche.isEnVenta());
        coche.getReparaciones().add(new Reparacion(this.tipo,this.coche));

    }

}
