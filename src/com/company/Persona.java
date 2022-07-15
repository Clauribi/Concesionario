package com.company;

public class Persona {
    private String nombre;
    private String direccion;
    private String dni;
    private int telefono;

    public Persona(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        setNombre(nombre);
        setDireccion(direccion);
        setDni(dni);
        setTelefono(telefono);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) throws ExceptionParametrosInvalidos {
        if(nombre==null) throw new ExceptionParametrosInvalidos("El nombre no puede ser null.");
        if(nombre.isEmpty()) throw new ExceptionParametrosInvalidos("El nombre no puede estar vacío.");
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) throws ExceptionParametrosInvalidos {
        if(direccion==null) throw new ExceptionParametrosInvalidos("La dirección no puede ser null.");
        if(direccion.isEmpty()) throw new ExceptionParametrosInvalidos("La dirección no puede estar vacía.");
        this.direccion = direccion;
    }

    public void setDni(String dni) throws ExceptionParametrosInvalidos {
        if(dni==null) throw new ExceptionParametrosInvalidos("El DNI no puede ser null.");
        if(dni.isEmpty()) throw new ExceptionParametrosInvalidos("El DNI no puede estar vacía.");
        this.dni = dni;
    }

    public void setTelefono(int telefono) throws ExceptionParametrosInvalidos {
        if (Integer.toString(telefono).length() != 9)
            throw new ExceptionParametrosInvalidos("El teléfono tiene que tener 9 dígitos.");
        this.telefono = telefono;
    }
    public void updateInfo(String nombre, String direccion, int telefono) throws ExceptionParametrosInvalidos {
        setNombre(nombre);
        setDireccion(direccion);
        setTelefono(telefono);
    }
    public String getInfo(){
        return "\nNombre: " + this.getNombre() +
                "\nDNI: " + this.getDni() +
                "\nTeléfono: " + this.getTelefono() +
                "\nDirección: " + this.getDireccion() + "\n";
    }
}
