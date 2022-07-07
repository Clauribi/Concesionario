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
    public void deleteCoche() throws ExceptionParametrosInvalidos {
        System.out.println("matricula:");
        String matricula = sc.next();
        if (!listadoCochesTotalesDefinitivo.containsKey(matricula)) {
            throw new ExceptionParametrosInvalidos("No existe el coche con esa matricula.");
        } else {
            System.out.println("¿Estas seguro de querer dar de baja a este coche?");
            System.out.println("1.-Sí");
            System.out.println("2.-No");
            int opcion;
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    this.listadoCochesTotalesDefinitivo.remove(matricula);
                    System.out.println("Se ha dado de baja el coche '" + matricula + "'.");
                    break;
                case 2:
                    System.out.println("No se da de baja.");
                    break;
            }
        }
    }
    public void changeCoche() throws ExceptionParametrosInvalidos {
        System.out.println("matricula:");
        String matricula = sc.next();
        if (!listadoVendedores.containsKey(matricula)) {
            throw new ExceptionParametrosInvalidos("No existe el coche con esa matricula.");
        } else {
            System.out.println("Introduzca de nuevo los datos del coche con sus modificaciones.");
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
            Coche cocheChange = new Coche(marca, modelo, matricula, compra, venta, t, exposicion);
            this.listadoCochesTotalesDefinitivo.put(matricula, cocheChange);
        }
    }
    public ArrayList getListadoCoches() {
        Collection<Coche> valores = this.listadoCochesTotalesDefinitivo.values();
        ArrayList<Coche> listadoTotalCoches = new ArrayList<>(valores);
        return listadoTotalCoches;
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
    public void deleteVendedorComision() throws ExceptionParametrosInvalidos {
        System.out.println("DNI:");
        String dni = sc.next();
        if (!listadoVendedores.containsKey(dni)) {
            throw new ExceptionParametrosInvalidos("No existe el vendedor con ese DNI.");
        } else {
            System.out.println("¿Estas seguro de querer dar de baja a este vendedor?");
            System.out.println("1.-Sí");
            System.out.println("2.-No");
            int opcion;
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    this.listadoVendedores.remove(dni);
                    System.out.println("Se ha dado de baja el vendedor '" + dni + "'.");
                    break;
                case 2:
                    System.out.println("No se da de baja.");
                    break;
            }
        }
    }
    public void changeVendedorComision() throws ExceptionParametrosInvalidos {
            System.out.println("DNI:");
            String dni = sc.next();
        if (!listadoVendedores.containsKey(dni)) {
            throw new ExceptionParametrosInvalidos("No existe el vendedor con ese DNI.");
        } else {
            System.out.println("Introduzca de nuevo los datos del vendedor con sus modificaciones.");
            System.out.println("Nombre:");
            String nombre = sc.next();
            System.out.println("Dirección:");
            String direccion = sc.next();
            System.out.println("Teléfono:");
            int telefono = sc.nextInt();
            VendedorComision vChange = new VendedorComision(nombre, direccion, dni, telefono);
            listadoVendedores.put(dni, vChange);
        }
    }

    public ArrayList getListadoVendedores() {
        Collection<VendedorComision> valores = this.listadoVendedores.values();
        ArrayList<VendedorComision> listadoTotal = new ArrayList<>(valores);
        return listadoTotal;
    }
    public void addCliente() throws ExceptionParametrosInvalidos {
        System.out.println("DNI:");
        String dni = sc.next();
        if (listadoClientes.containsKey(dni))
            throw new ExceptionParametrosInvalidos("Ya hay un cliente con ese DNI.");
        System.out.println("Nombre:");
        String nombre = sc.next();
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        Cliente c1 = new Cliente(nombre, direccion, dni, telefono);
        listadoClientes.put(dni, c1);
    }
    public void deleteCliente() throws ExceptionParametrosInvalidos {
        System.out.println("DNI:");
        String dni = sc.next();
        if (!listadoClientes.containsKey(dni)) {
            throw new ExceptionParametrosInvalidos("No existe el cliente con ese DNI.");
        } else {
            System.out.println("¿Estas seguro de querer dar de baja a este cliente?");
            System.out.println("1.-Sí");
            System.out.println("2.-No");
            int opcion;
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    this.listadoClientes.remove(dni);
                    System.out.println("Se ha dado de baja el cliente '" + dni + "'.");
                    break;
                case 2:
                    System.out.println("No se da de baja.");
                    break;
            }
        }
    }
    public void changeCliente() throws ExceptionParametrosInvalidos {
        System.out.println("DNI:");
        String dni = sc.next();
        if (!listadoClientes.containsKey(dni)) {
            throw new ExceptionParametrosInvalidos("No existe el cliente con ese DNI.");
        } else {
            System.out.println("Introduzca de nuevo los datos del cliente con sus modificaciones.");
            System.out.println("Nombre:");
            String nombre = sc.next();
            System.out.println("Dirección:");
            String direccion = sc.next();
            System.out.println("Teléfono:");
            int telefono = sc.nextInt();
            Cliente cChange = new Cliente(nombre, direccion, dni, telefono);
            listadoClientes.put(dni, cChange);
        }
    }
    public ArrayList getListadoClientes() {
        Collection<Cliente> valores = this.listadoClientes.values();
        ArrayList<Cliente> listadoTotalClientes = new ArrayList<>(valores);
        return listadoTotalClientes;
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
            System.out.println("1.-Gestión coches.");
            System.out.println("2.-Consultar coches en una exposición.");
            System.out.println("3.-Gestión vendedores.");
            System.out.println("3.-Gestión clientes.");
            System.out.println("4.-Consultar listado vendedores.");
            System.out.println("9.-Salir.");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("1.-Dar de alta un coche.");
                    System.out.println("2.-Dar de baja un coche.");
                    System.out.println("3.-Modificar un coche.");
                    System.out.println("4.-Visualizar los coches");
                    int option;
                    option= sc.nextInt();
                    switch (option){
                        case 1:
                            addCoche();
                            break;
                        case 2:
                            deleteCoche();
                            break;
                        case 3:
                            changeCoche();
                            break;
                        case 4:
                            getListadoCoches();
                        default:
                            System.out.println("Opción incorrecta.");
                    }
                case 2:
                    System.out.println("Indica la exposición:");
                    int expo = sc.nextInt();
                    System.out.println(getCochesPorExposicion(expo));
                    break;
                case 3:
                    System.out.println("1.-Dar de alta un vendedor.");
                    System.out.println("2.-Dar de baja un vendedor.");
                    System.out.println("3.-Modificar un vendedor.");
                    System.out.println("4.-Visualizar los vendedores");
                    option= sc.nextInt();
                    switch (option){
                        case 1:
                            addVendedorComision();
                            break;
                        case 2:
                            deleteVendedorComision();
                            break;
                        case 3:
                            changeVendedorComision();
                            break;
                        case 4:
                            getListadoVendedores();
                            break;
                        default:
                            System.out.println("Opción incorrecta.");
                    }
                case 4:
                    System.out.println("1.-Dar de alta un cliente.");
                    System.out.println("2.-Dar de baja un cliente.");
                    System.out.println("3.-Modificar un cliente.");
                    System.out.println("4.-Visualizar los clientes.");
                    option= sc.nextInt();
                    switch (option){
                        case 1:
                            addCliente();
                            break;
                        case 2:
                            deleteCliente();
                            break;
                        case 3:
                            changeCliente();
                            break;
                        case 4:
                            getListadoClientes();
                            break;
                        default:
                            System.out.println("Opción incorrecta.");
                    }

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
