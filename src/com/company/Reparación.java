package com.company;

public class Reparación {
    private String tipo;

    public Reparación(String tipo) throws ExceptionParametrosInvalidos {
        if (tipo != "mecanica" || tipo != "electrica" || tipo != "chapa" || tipo != "revision")
            throw new ExceptionParametrosInvalidos("Los tipos de reparación tienen que ser: 'mecanica', 'electrica', 'chapa' o 'revision'");
        this.tipo = tipo;
    }
}
