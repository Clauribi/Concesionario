package com.company;

import java.util.*;

public class Concesionario {
    private HashMap<Integer, Exposicion> listadoExposiciones;
    private HashMap<String, Coche> listadoCochesTotalesDefinitivo;
    private HashMap<String, VendedorComision> listadoVendedores;
    private HashMap<String, Cliente> listadoClientes;

    public HashMap<Integer, Exposicion> getListadoExposiciones() {
        return listadoExposiciones;
    }

    public HashMap<String, Coche> getListadoCochesTotalesDefinitivo() {
        return listadoCochesTotalesDefinitivo;
    }

    public HashMap<String, VendedorComision> getListadoVendedores() {
        return listadoVendedores;
    }

    public HashMap<String, Cliente> getListadoClientes() {
        return listadoClientes;
    }

    public Concesionario() throws ExceptionParametrosInvalidos {
        Exposicion expo = new Exposicion(1, "direccion", 123456789);
        this.listadoExposiciones = new HashMap<>();
        this.listadoCochesTotalesDefinitivo = new HashMap<>();
        this.listadoClientes = new HashMap<>();
        this.listadoVendedores = new HashMap<>();
        this.listadoExposiciones.put(expo.getNumExposicion(), expo);
    }

    public void existeVendedor(String dni) throws ExceptionParametrosInvalidos {
        if (!listadoVendedores.containsKey(dni)) throw new ExceptionParametrosInvalidos("No existe vendedor.");
    }

    public void existeCliente(String dni) throws ExceptionParametrosInvalidos {
        if (!listadoClientes.containsKey(dni)) throw new ExceptionParametrosInvalidos("No existe cliente.");
    }

    public void existeCoche(String matricula) throws ExceptionParametrosInvalidos {
        if (!listadoCochesTotalesDefinitivo.containsKey(matricula))
            throw new ExceptionParametrosInvalidos("No existe coche.");
    }

    public void existeExposicion(int numExpo) throws ExceptionParametrosInvalidos {
        if (!listadoExposiciones.containsKey(numExpo)) throw new ExceptionParametrosInvalidos("No existe exposición.");
    }

    public void addVendedorComision(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        VendedorComision v1 = new VendedorComision(nombre, direccion, dni, telefono);
        this.listadoVendedores.put(dni, v1);
    }

    public void deleteVendedorComision(String dni) throws ExceptionParametrosInvalidos {
        VendedorComision v1 = listadoVendedores.get(dni);
        if (v1.getCochesVendidos().isEmpty()) {
            this.listadoVendedores.remove(dni);
        } else throw new ExceptionParametrosInvalidos("El vendedor tiene coches vendidos. NO SE PUEDE BORRAR.");
    }

    public void changeVendedorComision(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        VendedorComision v = listadoVendedores.get(dni);
        v.updateInfo(nombre, direccion, telefono);
    }

    public void addExposicion(int numExpo, String direccion, int telefono) throws ExceptionParametrosInvalidos {
        Exposicion ex1 = new Exposicion(numExpo, direccion, telefono);
        this.listadoExposiciones.put(numExpo, ex1);
    }

    public void deleteExposicion(int numExpo) {
        this.listadoExposiciones.remove(numExpo);
    }

    public void changeExposicion(int numExpo, String direccion, int telefono) throws ExceptionParametrosInvalidos {
        Exposicion expo = listadoExposiciones.get(numExpo);
        expo.updateInfo(direccion, telefono);
    }


    public void changeCocheExposicion() throws ExceptionParametrosInvalidos {
        int expo = 0;
        Coche c = null;
        String matricula = null;
        boolean noVenta = false;
        boolean existe = false;
        while (!noVenta) {
            System.out.println("Indica la matrícula del coche a cambiar: ");
            for (Coche lista : listadoCochesTotalesDefinitivo.values()) {
                if (lista.getEstado() == EstadoCoche.enVenta) {
                    System.out.println(lista.getMatricula());
                }
            }
            matricula = sc.next();
            if (!listadoCochesTotalesDefinitivo.containsKey(matricula)) {
                System.out.println("La matrícula no está en la lista. Indica una matrícula correcta o escribe 'salir' para cancelar.");
                matricula = sc.next();
                if (matricula == "salir") {
                    noVenta = true;
                }
            }
            c = listadoCochesTotalesDefinitivo.get(matricula);
            noVenta = true;
        }
        if (c == null) existe = true;
        while (!existe) {
            System.out.println("Indica la exposición de destino:");
            for (Exposicion lista : listadoExposiciones.values()) {
                System.out.println(lista.getNumExposicion());
            }
            expo = sc.nextInt();
            if (!listadoExposiciones.containsKey(expo)) {
                System.out.println("La exposición no existe.");
            } else if (c.getExposicion().getNumExposicion() == expo) {
                System.out.println("El cambio no se va a realizar porque el coche ya estaba en esa exposición");
                existe = true;
            } else {
                Exposicion exposicion = listadoExposiciones.get(expo);
                try {
                    c.cambiarExposicion(exposicion);
                } catch (ExceptionParametrosInvalidos e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Se ha realizado el cambio del coche con matrícula " + c.getMatricula() + " a la siguiente exposición:\n"
                        + exposicion.getInfo());
                existe = true;
            }
        }
    }

    public void addCoche(String marca, String modelo, String matricula, double compra, double venta, TipoCoche t, Exposicion exposicion) throws ExceptionParametrosInvalidos {
        Coche cocheNew = new Coche(marca, modelo, matricula, compra, venta, t, exposicion);
        this.listadoCochesTotalesDefinitivo.put(cocheNew.getMatricula(), cocheNew);
    }

    public void deleteCoche(String matricula) throws ExceptionParametrosInvalidos {
        this.listadoCochesTotalesDefinitivo.remove(matricula);

    }

    public void changeCoche(String marca, String modelo, String matricula, double compra, double venta, TipoCoche t, Exposicion exposicion) throws ExceptionParametrosInvalidos {
        Coche c = listadoCochesTotalesDefinitivo.get(matricula);
        c.updateInfo(marca, modelo, compra, venta, t, exposicion);
    }

    public void addCliente(String dni, String nombre, String direccion, int telefono) throws ExceptionParametrosInvalidos {
        Cliente c1 = new Cliente(nombre, direccion, dni, telefono);
        listadoClientes.put(dni, c1);
    }

    public void deleteCliente(String dni) throws ExceptionParametrosInvalidos {
        Cliente c1 = listadoClientes.get(dni);
        if (c1.getComprados().isEmpty() && c1.getReservados().isEmpty()) {
            this.listadoVendedores.remove(dni);
        } else
            throw new ExceptionParametrosInvalidos("El cliente tiene coches reservados o comprados. NO SE PUEDE BORRAR.");
    }

    public void changeCliente(String nombre, String direccion, String dni, int telefono) throws ExceptionParametrosInvalidos {
        Cliente c = listadoClientes.get(dni);
        c.updateInfo(nombre, direccion, telefono);

    }
}


