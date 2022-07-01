package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Exposicion {
    private int numExposicion;
    private String direccion;
    private int telefono;
    private Coche coche;
    private HashMap<String, Coche> cochesExpo;
    private HashMap<Integer, Exposicion> listasExpos;

    public HashMap<Integer, Exposicion> getListasExpos() {
        return listasExpos;
    }

    public int getNumExposicion() {
        return numExposicion;
    }

    public void setNumExposicion(int numExposicion) {
        this.numExposicion = numExposicion;
    }

    public Exposicion(int numExposicion, String direccion, int telefono) throws ExceptionParametrosInvalidos {
        if (numExposicion == 0) throw new ExceptionParametrosInvalidos("El número de la exposición no puede ser 0.");
        this.numExposicion = numExposicion;
        if (direccion == null) throw new ExceptionParametrosInvalidos("La dirección no puede ser null.");
        this.direccion = direccion;
        if (Integer.toString(telefono).length() != 9)
            throw new ExceptionParametrosInvalidos("El teléfono tiene que tener 9 dígitos.");
        this.telefono = telefono;
        this.listasExpos.put(numExposicion,new Exposicion(numExposicion,direccion,telefono));
    }

    public void addCoche(String matricula) throws ExceptionParametrosInvalidos {
       if(coche.getCochesVenta().containsKey(matricula)){
          coche = coche.getCochesVenta().get(matricula);
          coche.setExposicion(1);
        this.cochesExpo.put(matricula,coche);

       }

    }

   /*   public void borrarCoche(Coche coche) throws ExceptionParametrosInvalidos {
      if (!this.listadoCoches.contains(coche))
            throw new ExceptionParametrosInvalidos("El coche no esta en la posicion indicada.");
        this.listadoCoches.remove(coche);
    }*/
}
