package com.company;

public class Exposicion {
    private int numExposicion;
    private String direccion;
    private int telefono;

    public void setNumExposicion(int numExposicion) throws ExceptionParametrosInvalidos {
        if (numExposicion == 0) throw new ExceptionParametrosInvalidos("El número de la exposición no puede ser 0.");
        this.numExposicion = numExposicion;
    }

    public void setDireccion(String direccion) throws ExceptionParametrosInvalidos {
        if (direccion == null) throw new ExceptionParametrosInvalidos("La dirección no puede ser null.");
        if (direccion.isEmpty()) throw new ExceptionParametrosInvalidos("La dirección no puede estar vacía.");
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) throws ExceptionParametrosInvalidos {
        if (Integer.toString(telefono).length() != 9)
            throw new ExceptionParametrosInvalidos("El teléfono tiene que tener 9 dígitos.");
        this.telefono = telefono;
    }

    public int getNumExposicion() {
        return numExposicion;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public Exposicion(int numExposicion, String direccion, int telefono) throws ExceptionParametrosInvalidos {
        setNumExposicion(numExposicion);
        setDireccion(direccion);
        setTelefono(telefono);
    }

    public void updateInfo(String direccion, int telefono) throws ExceptionParametrosInvalidos {
        setDireccion(direccion);
        setTelefono(telefono);
    }

    public String getInfo() {
        return "\nExposición número " + this.numExposicion +
                "\nDirección: " + this.direccion +
                "\nTeléfono: " + this.telefono + "\n";
    }
}
