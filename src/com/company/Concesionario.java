package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Concesionario {
    private DirectorComercial d1;
    private HashMap<Integer, Exposicion> listadoExposiciones;
    private HashMap<String, Coche> listadoCochesTotalesDefinitivo;
    private HashMap<String, VendedorComision> listadoVendedores;
    private HashMap<String, Cliente> listadoClientes;
    private Scanner sc = new Scanner(System.in);

    public Concesionario() throws ExceptionParametrosInvalidos {
        Exposicion expo = new Exposicion(1, "direccion", 123456789);
        this.listadoExposiciones = new HashMap<>();
        this.listadoCochesTotalesDefinitivo = new HashMap<>();
        this.listadoClientes = new HashMap<>();
        this.listadoVendedores = new HashMap<>();
        this.listadoExposiciones.put(expo.getNumExposicion(), expo);
    }

    public void addCoche() throws ExceptionParametrosInvalidos {
        System.out.println("Matricula:");
        String matricula = sc.next();
        System.out.println("Modelo:");
        String modelo = sc.next();
        System.out.println("Marca:");
        String marca = sc.next();
        System.out.println("Compra:");
        double compra = sc.nextDouble();
        System.out.println("Venta:");
        double venta = sc.nextDouble();
        System.out.println("Elige el tipo:");
        System.out.println("1.-Industrial.");
        System.out.println("2.-Todoterreno.");
        System.out.println("3.-Turismo.");
        TipoCoche t = null;
        int tipo;
        tipo = sc.nextInt();
        switch (tipo) {
            case 1:
                t = TipoCoche.industrial;
                break;
            case 2:
                t = TipoCoche.todoterreno;
                break;
            case 3:
                t = TipoCoche.turismo;
                break;
            default:
                System.out.println("Tipo erroneo");
                break;
        }
        System.out.println("Nº exposición: ");
        int expo = sc.nextInt();
        if (!listadoExposiciones.containsKey(expo)) throw new ExceptionParametrosInvalidos("No existe la exposición");
        Exposicion exposicion = listadoExposiciones.get(expo);
        Coche cocheNew = new Coche(marca, modelo, matricula, compra, venta, t, exposicion);
        this.listadoCochesTotalesDefinitivo.put(cocheNew.getMatricula(), cocheNew);
    }

    public ArrayList getCochesPorExposicion(int expo) {
        Collection<Coche> valores = this.listadoCochesTotalesDefinitivo.values();
        ArrayList<Coche> listaCoches = new ArrayList<>(valores);
        ArrayList<Coche> listaCochesEnExposicion = new ArrayList<>();
        for (Coche c : listaCoches) {
            if (c.getExposicion().getNumExposicion() == expo) {
                listaCochesEnExposicion.add(c);
            }
        }
        return listaCochesEnExposicion;
    }

    public void addVendedorComision() throws ExceptionParametrosInvalidos {
        System.out.println("DNI:");
        String dni = sc.next();
        if (listadoVendedores.containsKey(dni))
            throw new ExceptionParametrosInvalidos("Ya hay un vendedor con ese DNI.");
        System.out.println("Nombre:");
        String nombre = sc.next();
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        VendedorComision v1 = new VendedorComision(nombre, direccion, dni, telefono);
        listadoVendedores.put(dni, v1);
    }

    public ArrayList getListadoVendedores() {
        Collection<VendedorComision> valores = this.listadoVendedores.values();
        ArrayList<VendedorComision> listadoTotal = new ArrayList<>(valores);
        return listadoTotal;
    }

    public void menu() throws ExceptionParametrosInvalidos {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {
            System.out.println("¿Quién eres?");
            System.out.println("1.-Vendedor.");
            System.out.println("2.-Director comercial.");
            System.out.println("3.-Mecánico.");
            System.out.println("4.-Salir.");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    gestionVendedoresComision();
                    break;
                case 2:
                    gestionDirectorComercial();
                    break;
                case 3:
                    gestionMecanico();
                    break;
                case 4:
                    System.out.println("Saliendo.....");
                    salir = true;
                    break;
                default:
                    System.out.println("Indica una opción correcta.");
                    break;
            }
        }
    }

    public void gestionVendedoresComision() throws ExceptionParametrosInvalidos {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        System.out.println("Indica tu dni para acceder:");
        String dni = sc.next();
        if (!listadoVendedores.containsKey(dni)) throw new ExceptionParametrosInvalidos("No está dado de alta.");
        String matricula;
        VendedorComision v1 = listadoVendedores.get(dni);
        System.out.println("Bienvenido " + v1.getNombre());
        while (!salir) {
            System.out.println("Indica la acción a realizar:");
            System.out.println("1.-Vender un coche.");
            System.out.println("2.-Reservar un coche.");
            System.out.println("3.-Consultar clientes.");
            System.out.println("4.-Consultar coches.");
            System.out.println("5.-Consultar exposiciones.");
            System.out.println("6.-Salir.");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Indica la matricula del coche que quieres vender.");
                    matricula = sc.next();
//                    v1.venderCoche(matricula);
                    break;
                case 2:
                    System.out.println("Indica la matricula del coche que quieres reservar.");
                    matricula = sc.next();
//                    v1.reservarCoche(matricula);
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("opcion incorrecta");
                    break;
            }
        }
    }

    public void gestionDirectorComercial() throws ExceptionParametrosInvalidos {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        if (d1 == null) {
            System.out.println("Nombre: ");
            String nombre = sc.next();
            System.out.println("Dirección: ");
            String direccion = sc.next();
            System.out.println("DNI: ");
            String dni = sc.next();
            System.out.println("Teléfono: ");
            int telefono = sc.nextInt();
            d1 = new DirectorComercial(nombre, direccion, dni, telefono);
        }
        while (!salir) {
            System.out.println("Bienvenido " + d1.getNombre());
            System.out.println("Elige que deseas hacer.");
            System.out.println("1.-Añadir un coche.");
            System.out.println("2.-Consultar coches en una exposición.");
            System.out.println("3.-Añadir vendedor.");
            System.out.println("4.-Consultar listado vendedores,");
            System.out.println("9.-Salir.");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    addCoche();
                    break;
                case 2:
                    System.out.println("Indica la exposición:");
                    int expo = sc.nextInt();
                    System.out.println(getCochesPorExposicion(expo));
                    break;
                case 3:
                    addVendedorComision();
                    break;
                case 4:
                    getListadoVendedores();
                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }

    public void gestionMecanico() throws ExceptionParametrosInvalidos {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        String matricula;
        String nombre = sc.next();
        String direccion = sc.next();
        String dni = sc.next();
        int telefono = sc.nextInt();
        Mecanico m = new Mecanico(nombre, direccion, dni, telefono);
        while (!salir) {
            System.out.println("Elige que deseas hacer.");
            System.out.println("1.-Consultar reparaciones.");
            System.out.println("2.-Reparar coche.");
            System.out.println("3.-Salir.");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    matricula = sc.next();
                    m.consultaReparaciones(matricula);
                    break;
                case 2:
                    System.out.println("Que coche:");
                    matricula = sc.next();
                    System.out.println("Que tipo:");
                    System.out.println("1.-Mecanica.");
                    System.out.println("2.-Electrica.");
                    System.out.println("3.-Chapa.");
                    System.out.println("4.-Revision.");
                    TipoReparacion t = null;
                    int tipo;
                    tipo = sc.nextInt();
                    switch (tipo) {
                        case 1:
                            t = TipoReparacion.mecanica;
                            break;
                        case 2:
                            t = TipoReparacion.chapa;
                            break;
                        case 3:
                            t = TipoReparacion.electrica;
                            break;
                        case 4:
                            t = TipoReparacion.revision;
                            break;
                        default:
                            System.out.println("Tipo erroneo");
                            break;
                    }
                    m.repararCoche(t, matricula);
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("opcion incorrecta");
                    break;
            }
        }
    }
}
