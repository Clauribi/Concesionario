package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private Concesionario concesionario = new Concesionario();

    public Menu() throws ExceptionParametrosInvalidos {
    }

    private void altaVendedorComision() throws ExceptionParametrosInvalidos {
        System.out.println("DNI:");
        String dni = sc.next();
        if (concesionario.getListadoVendedores().containsKey(dni))
            throw new ExceptionParametrosInvalidos("Ya hay un vendedor con ese DNI.");
        System.out.println("Nombre:");
        String nombre = sc.next();
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        concesionario.addVendedorComision(nombre, direccion, dni, telefono);
    }

    private void bajaVendedorComision() throws ExceptionParametrosInvalidos {
        System.out.println("DNI:");
        String dni = sc.next();
        concesionario.existeVendedor(dni);
        System.out.println("¿Estas seguro de querer dar de baja a este vendedor?");
        System.out.println("1.-Sí");
        System.out.println("2.-No");
        int opcion;
        opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                concesionario.deleteVendedorComision(dni);
                System.out.println("Se ha dado de baja el vendedor '" + dni + "'.");
                break;
            case 2:
                System.out.println("No se da de baja.");
                break;
            default:
                System.out.println("Opción incorrecta.");
                break;
        }
    }

    private void modificarVendedorComision() throws ExceptionParametrosInvalidos {
        System.out.println("DNI:");
        String dni = sc.next();
        concesionario.existeVendedor(dni);
        System.out.println("Introduzca de nuevo los datos del vendedor con sus modificaciones.");
        System.out.println("Nombre:");
        String nombre = sc.next();
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        concesionario.changeVendedorComision(nombre, direccion, dni, telefono);
    }

    private void altaExposicion() throws ExceptionParametrosInvalidos {
        System.out.println("Número de exposición:");
        Integer numExpo = sc.nextInt();
        if (concesionario.getListadoExposiciones().containsKey(numExpo)) {
            throw new ExceptionParametrosInvalidos("Ya hay una exposición con ese número.");
        }
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        concesionario.addExposicion(numExpo, direccion, telefono);
    }

    private void bajaExposicion() throws ExceptionParametrosInvalidos {
        System.out.println("Número de exposición:");
        Integer numExpo = sc.nextInt();
        concesionario.existeExposicion(numExpo);
        System.out.println("¿Estas seguro de querer dar de baja esta exposición?");
        System.out.println("1.-Sí");
        System.out.println("2.-No");
        int opcion;
        opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                concesionario.deleteExposicion(numExpo);
                System.out.println("Se ha dado de baja la exposición '" + numExpo + "'.");
                break;
            case 2:
                System.out.println("No se da de baja.");
                break;
            default:
                System.out.println("Opción incorrecta.");
                break;
        }
    }

    private void modificarExposicion() throws ExceptionParametrosInvalidos {
        System.out.println("Número de exposición a modificar:");
        Integer numExpo = sc.nextInt();
        concesionario.existeExposicion(numExpo);
        System.out.println("Introduzca de nuevo los datos de la exposición con sus modificaciones.");
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        concesionario.changeExposicion(numExpo, direccion, telefono);
    }

    private void verDatosExposicion() throws ExceptionParametrosInvalidos {
        System.out.println("Las exposiciones disponibles son las siguientes: ");
        System.out.println(concesionario.verListaExposiciones());
        System.out.println("Indica el número de exposición para ver sus datos: ");
        int numExpo = sc.nextInt();
        concesionario.existeExposicion(numExpo);
        System.out.println("Los datos de la exposición son: ");
        System.out.println(concesionario.verExpo(numExpo) + "\n" + concesionario.verCochesExpo(numExpo));
    }

    public void cambiarCocheExposicion(String matricula, int numExpo) {
        Coche c = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
        Exposicion exposicion = concesionario.getListadoExposiciones().get(numExpo);
        try {
            c.cambiarExposicion(exposicion);
        }catch (ExceptionParametrosInvalidos e){
            System.out.println(e.getMessage());
        }
    }

    private void menuCambiarCocheExposicion() throws ExceptionParametrosInvalidos {
        Coche c = null;
        String matricula = null;
        int numExpo = 0;
        boolean noVenta = false;
        boolean existe = false;
        while (!noVenta) {
            System.out.println("Indica la matrícula del coche a cambiar: ");
            System.out.println(concesionario.verCochesVenta());
            matricula = sc.next();
            if (!concesionario.enVenta(matricula)) {
                System.out.println("La matrícula no está en la lista. Indica una matrícula correcta o escribe 'salir' para cancelar.");
                matricula = sc.next();
                if (matricula == "salir") {
                    noVenta = true;
                }
            }
            noVenta = true;
        }
        if (c == null) existe = true;
        while (!existe) {
            System.out.println("Indica la exposición de destino:");
            System.out.println(concesionario.verListaExposiciones());
            numExpo = sc.nextInt();
            concesionario.existeExposicion(numExpo);
            if (c.getExposicion().getNumExposicion() == numExpo) {
                System.out.println("El cambio no se va a realizar porque el coche ya estaba en esa exposición");
            } else {
                Exposicion exposicion = concesionario.getListadoExposiciones().get(numExpo);
                System.out.println("Se ha realizado el cambio del coche con matrícula " + c.getMatricula() + " a la siguiente exposición:\n"
                        + exposicion.getInfo());
            }
            existe = true;
        }
        cambiarCocheExposicion(matricula, numExpo);
    }

    private void altaCoche() throws ExceptionParametrosInvalidos {
        System.out.println("Matricula:");
        String matricula = sc.next();
        System.out.println("Marca:");
        String marca = sc.next();
        System.out.println("Modelo:");
        String modelo = sc.next();
        System.out.println("Precio de compra:");
        double compra = sc.nextDouble();
        System.out.println("Precio de venta:");
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
                System.out.println("Tipo erróneo");
                break;
        }
        System.out.println("Nº exposición: ");
        int expo = sc.nextInt();
        if (!concesionario.getListadoExposiciones().containsKey(expo))
            throw new ExceptionParametrosInvalidos("No existe la exposición");
        Exposicion exposicion = concesionario.getListadoExposiciones().get(expo);
        concesionario.addCoche(marca, modelo, matricula, compra, venta, t, exposicion);
    }

    private void bajaCoche() throws ExceptionParametrosInvalidos {
        System.out.println("Matrícula:");
        String matricula = sc.next();
        concesionario.existeCoche(matricula);
        System.out.println("¿Estas seguro de querer dar de baja a este coche?");
        System.out.println("1.-Sí");
        System.out.println("2.-No");
        int opcion;
        opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                concesionario.deleteCoche(matricula);
                System.out.println("Se ha dado de baja el coche '" + matricula + "'.");
                break;
            case 2:
                System.out.println("No se da de baja.");
                break;
            default:
                System.out.println("Opción incorrecta.");
                break;
        }
    }

    private void modificarCoche() throws ExceptionParametrosInvalidos {
        System.out.println("Matrícula:");
        String matricula = sc.next();
        concesionario.existeCoche(matricula);
        System.out.println("Introduzca de nuevo los datos del coche con sus modificaciones.");
        System.out.println("Marca:");
        String marca = sc.next();
        System.out.println("Modelo:");
        String modelo = sc.next();
        System.out.println("Precio de compra:");
        double compra = sc.nextDouble();
        System.out.println("Precio de venta:");
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
                System.out.println("Tipo erróneo");
                break;
        }
        System.out.println("Nº exposición: ");
        int expo = sc.nextInt();
        concesionario.existeExposicion(expo);
        Exposicion exposicion = concesionario.getListadoExposiciones().get(expo);
        concesionario.changeCoche(marca, modelo, matricula, compra, venta, t, exposicion);
    }

    private String verCochesConcesionario() {
        ArrayList<String> totalCoches = new ArrayList<>();
        for (Coche c : concesionario.getListadoCochesTotalesDefinitivo().values()) {
            totalCoches.add(c.getInfo());
        }
        return totalCoches.toString();
    }

    private void altaCliente() throws ExceptionParametrosInvalidos {
        System.out.println("DNI:");
        String dni = sc.next();
        if (concesionario.getListadoClientes().containsKey(dni))
            throw new ExceptionParametrosInvalidos("Ya hay un cliente con ese DNI.");
        System.out.println("Nombre:");
        String nombre = sc.next();
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        concesionario.addCliente(dni, nombre, direccion, telefono);
    }

    private void bajaCliente() throws ExceptionParametrosInvalidos {
        System.out.println("DNI:");
        String dni = sc.next();
        concesionario.existeCliente(dni);
        System.out.println("¿Estas seguro de querer dar de baja a este cliente?");
        System.out.println("1.-Sí");
        System.out.println("2.-No");
        int opcion;
        opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                concesionario.deleteCliente(dni);
                System.out.println("Se ha dado de baja el cliente '" + dni + "'.");
                break;
            case 2:
                System.out.println("No se da de baja.");
                break;
            default:
                System.out.println("Opción incorrecta.");
                break;
        }
    }

    private void modificarCliente() throws ExceptionParametrosInvalidos {
        System.out.println("DNI:");
        String dni = sc.next();
        concesionario.existeCliente(dni);
        System.out.println("Introduzca de nuevo los datos del cliente con sus modificaciones.");
        System.out.println("Nombre:");
        String nombre = sc.next();
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        concesionario.changeCliente(nombre, direccion, dni, telefono);
    }


    public void menu() {
        DirectorComercial d1 = null;
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        while (!salir) {
            System.out.println("¿Quién eres?");
            System.out.println("1.-Director comercial.");
            System.out.println("2.-Vendedor.");
            System.out.println("3.-Mecánico.");
            System.out.println("9.-Salir del programa.");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    gestionDirectorComercial(d1);
                    break;
                case 2:
                    try {
                        gestionVendedoresComision();
                    }catch (ExceptionParametrosInvalidos e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        gestionMecanico();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    System.out.println("Saliendo.....");
                    salir = true;
                    break;
                default:
                    System.out.println("Indica una opción correcta.");
                    break;
            }
        }
    }

    private void gestionDirectorComercial(DirectorComercial d1) {
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
            try {
                d1 = new DirectorComercial(nombre, direccion, dni, telefono);
            } catch (ExceptionParametrosInvalidos e) {
                System.out.println(e.getMessage());
            }
        }
        while (!salir) {
            System.out.println("Bienvenido " + d1.getNombre());
            System.out.println("Elige que deseas hacer.");
            System.out.println("1.-Gestión coches.");
            System.out.println("2.-Gestión exposiciones.");
            System.out.println("3.-Gestión vendedores.");
            System.out.println("4.-Gestión clientes.");
            System.out.println("5.-Consultas.");
            System.out.println("9.-Volver.");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    gestionCoches(sc);
                    break;
                case 2:
                    gestionExposiciones(sc);
                    break;
                case 3:
                    gestionVendedores(sc);
                    break;
                case 4:
                    gestionClientes(sc);
                    break;
                case 5:
                    try {
                        consultas(sc);
                    }catch (ExceptionParametrosInvalidos e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        }
    }

    private void consultas(Scanner sc) throws ExceptionParametrosInvalidos {
        boolean salir5 = false;
        while (!salir5) {
            System.out.println("1.-Coches en venta.");
            System.out.println("2.-Coches reservados.");
            System.out.println("3.-Coches en reparación.");
            System.out.println("4.-Coches vendidos por un vendedor.");
            System.out.println("5.-Coches reservados por un cliente.");
            System.out.println("6.-Cliente de un coche vendido.");
            System.out.println("9.-Volver.");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println(concesionario.verCochesVenta());
                    break;
                case 2:
                    System.out.println(concesionario.verCochesReservados());
                    break;
                case 3:
                    System.out.println(concesionario.verCochesReparacion());
                    break;
                case 4:
                    System.out.println("Indica el DNI del vendedor a consultar: ");
                    String dni = sc.next();
                    concesionario.existeVendedor(dni);
                    VendedorComision v1 = concesionario.getListadoVendedores().get(dni);
                    System.out.println("El listado de coches vendidos por " + v1.getNombre() + " es:");
                    v1.getCochesVendidos().values().toString();
                    int sueldo = v1.getCochesVendidos().size() * 200;
                    System.out.println("El sueldo es: " + sueldo);
                    break;
                case 5:
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Elige el coche que quieres consultar de la lista: ");
                    System.out.println(concesionario.verCochesVendidos());
                    String matricula = sc.next();
                    System.out.println(mostrarCliente(matricula));
                    break;
                case 9:
                    salir5 = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        }
    }

    private void gestionClientes(Scanner sc) {
        boolean salir4 = false;
        while (!salir4) {
            System.out.println("1.-Dar de alta un cliente.");
            System.out.println("2.-Dar de baja un cliente.");
            System.out.println("3.-Modificar un cliente.");
            System.out.println("4.-Visualizar los clientes.");
            System.out.println("9.-Volver.");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        altaCliente();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        bajaCliente();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        modificarCliente();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
//                    getListadoClientes();
                    break;
                case 9:
                    salir4 = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        }
    }

    private void gestionVendedores(Scanner sc) {
        boolean salir3 = false;
        while (!salir3) {
            System.out.println("1.-Dar de alta un vendedor.");
            System.out.println("2.-Dar de baja un vendedor.");
            System.out.println("3.-Modificar un vendedor.");
            System.out.println("4.-Visualizar los vendedores.");
            System.out.println("9.-Volver.");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        altaVendedorComision();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        bajaVendedorComision();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        modificarVendedorComision();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
//                    System.out.println(getDatosVendedores());
                    break;
                case 9:
                    salir3 = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        }
    }

    private void gestionExposiciones(Scanner sc) {
        int expo;
        boolean salir2 = false;
        while (!salir2) {
            System.out.println("1.-Dar de alta una exposición.");
            System.out.println("2.-Dar de baja una exposición.");
            System.out.println("3.-Modificar una exposición.");
            System.out.println("4.-Visualizar datos de una exposición.");
//            System.out.println("5.-Visualizar los coches de una exposición.");
            System.out.println("6.-Cambiar coche de exposición.");
            System.out.println("9.-Volver.");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        altaExposicion();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        bajaExposicion();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        modificarExposicion();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        verDatosExposicion();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
//                case 5:
//                    System.out.println("Indica la exposición:");
//                    expo = sc.nextInt();
//                    System.out.println(getDatosCochesExpo(expo));
//                    break;
                case 6:
                    try {
                        menuCambiarCocheExposicion();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    salir2 = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        }
    }

    private void gestionCoches(Scanner sc) {
        boolean salir1 = false;
        while (!salir1) {
            System.out.println("1.-Dar de alta un coche.");
            System.out.println("2.-Dar de baja un coche.");
            System.out.println("3.-Modificar un coche.");
            System.out.println("4.-Visualizar los coches");
            System.out.println("9.-Volver.");
            int option;
            option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        altaCoche();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        bajaCoche();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        modificarCoche();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println(verCochesConcesionario());
                    break;
                case 9:
                    salir1 = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }

    private void gestionVendedoresComision() throws ExceptionParametrosInvalidos {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        System.out.println("Indica tu DNI para acceder:");
        String dni = sc.next();
        concesionario.existeVendedor(dni);
        String matricula;
        VendedorComision v1 = concesionario.getListadoVendedores().get(dni);
        System.out.println("Bienvenido " + v1.getNombre());
        while (!salir) {
            System.out.println("Indica la acción a realizar:");
            System.out.println("1.-Vender un coche.");
            System.out.println("2.-Reservar un coche.");
            System.out.println("3.-Consultar clientes.");
            System.out.println("4.-Consultar coches.");
            System.out.println("5.-Consultar exposiciones.");
            System.out.println("9.-Volver.");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Indica la matricula del coche que quieres vender.");
                    System.out.println("Listado de coches en Stock:");
                    System.out.println(concesionario.verCochesVenta());
                    System.out.println("Listado de coches reservados:");
                    System.out.println(concesionario.verCochesReservados());
                    matricula = sc.next();
                    Coche coche = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
                    System.out.println("Indica el DNI del cliente:");
                    dni = sc.next();
                    Cliente cliente = concesionario.getListadoClientes().get(dni);
                    try {
                        v1.venderCoche(coche, cliente);
                        cliente.agregarCocheComprado(coche);
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Indica la matricula del coche que quieres reservar.");
                    System.out.println("Listado de coches en Stock:");
                    System.out.println(concesionario.verCochesVenta());
                    matricula = sc.next();
                    concesionario.existeCoche(matricula);
                    coche = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
                    System.out.println("Indica el DNI del cliente:");
                    dni = sc.next();
                    concesionario.existeVendedor(dni);
                    cliente = concesionario.getListadoClientes().get(dni);
                    try {
                        v1.reservarCoche(coche, cliente);
                        cliente.agregarCocheReservado(coche);
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:

                    break;
                case 4:
                    System.out.println(verCochesConcesionario());
                    break;
                case 5:
                    try {
                        verDatosExposicion();
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        }
    }


    private void gestionMecanico() throws ExceptionParametrosInvalidos {
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
            System.out.println("9.-Volver.");
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
                    System.out.println("1.-Mecánica.");
                    System.out.println("2.-Eléctrica.");
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
                            System.out.println("Tipo erróneo");
                            break;
                    }
                    m.repararCoche(t, matricula);
                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        }
    }

    private String mostrarCliente(String matricula){
        Coche c = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
        return c.getCliente().toString();
    }


//    private ArrayList getListadoCoches() {
//        Collection<Coche> valores = this.listadoCochesTotalesDefinitivo.values();
//        ArrayList<Coche> listadoTotalCoches = new ArrayList<>(valores);
//        return listadoTotalCoches;
//    }







//    private ArrayList getListadoExposiciones() {
//        Collection<Exposicion> valores = this.listadoExposiciones.values();
//        ArrayList<Exposicion> listadoTotalExposiciones = new ArrayList<>(valores);
//        return listadoTotalExposiciones;
//    }
//
//    private String getDatosExposicion(int expo) {
//        if (listadoExposiciones.containsKey(expo)) {
//            Exposicion exposicion = listadoExposiciones.get(expo);
//            return exposicion.getInfo();
//        } else {
//            return "La exposición número " + expo + " no existe.";
//        }
//    }
//
//    private String getDatosCochesExpo(int expo) {
//        ArrayList<String> listadoCochesExposicion = new ArrayList<>();
//        for (Coche c : listadoCochesTotalesDefinitivo.values()) {
//            if (c.getExposicion().getNumExposicion() == expo) {
//                listadoCochesExposicion.add(c.getInfo());
//            }
//        }
//        return listadoCochesExposicion.toString();
//    }
//
//    private ArrayList getCochesPorExposicion(int expo) {
//        Collection<Coche> valores = this.listadoCochesTotalesDefinitivo.values();
//        ArrayList<Coche> listaCoches = new ArrayList<>(valores);
//        ArrayList<Coche> listaCochesEnExposicion = new ArrayList<>();
//        for (Coche c : listaCoches) {
//            if (c.getExposicion().getNumExposicion() == expo) {
//                listaCochesEnExposicion.add(c);
//            }
//        }
//        return listaCochesEnExposicion;
//    }
//
//    private String getDatosVendedores() {
//        ArrayList<String> datosVendedores = new ArrayList<>();
//        for (VendedorComision v : listadoVendedores.values()) {
//            datosVendedores.add(v.getInfo());
//        }
//        return datosVendedores.toString();
//    }
//
//
//
//    private String mostrarCliente(String matricula) {
//        Coche c = listadoCochesTotalesDefinitivo.get(matricula);
//        return c.getCliente().getNombre();
//    }
}
