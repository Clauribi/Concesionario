package com.company;

import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private Concesionario concesionario = new Concesionario();
    private DirectorComercial d1;
    private boolean salir = false;
    private boolean repetir = false;

    public Menu() throws ExceptionParametrosInvalidos {
        this.d1 = null;
        sc.useDelimiter("\n");
    }

    private void venderCoche(VendedorComision vendedor) {
        Coche coche = null;
        if (concesionario.listaCochesReservados().isEmpty() && concesionario.listaCochesStock().isEmpty()) {
            System.out.println("No hay coches disponibles en el concesionario para vender.");
        } else if (concesionario.getListadoClientes().isEmpty()) {
            System.out.println("No existen clientes para realizar la operación.");
        } else {
            if (!concesionario.listaCochesStock().isEmpty()) {
                System.out.println("Listado de coches en Stock:");
                System.out.println(concesionario.verCochesVenta());
            }
            if (!concesionario.listaCochesReservados().isEmpty()) {
                System.out.println("Listado de coches reservados:");
                System.out.println(concesionario.verCochesReservados());
            }
            System.out.println("Indica la matrícula del coche que quieres vender.");
            String matricula = sc.next();
            do {
                try {
                    concesionario.existeCoche(matricula);
                    coche = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
                    if (!concesionario.listaCochesReservados().contains(coche) && !concesionario.listaCochesStock().contains(coche)) {
                        throw new NullCocheException();
                    }
                    repetir = false;
                } catch (NullCocheException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Indica una matrícula de la lista o escriba 'salir' para volver.");
                    matricula = sc.next();
                    if (!matricula.equals("salir")) {
                        repetir = true;
                    } else if (matricula.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            if (coche != null) {
                if (concesionario.listaCochesReservados().contains(coche)) {
                    System.out.println("El coche está reservado por:" + coche.getCliente().getInfo());
                    do {
                        System.out.println("¿Desea efectuar la venta del vehículo?");
                        System.out.println("1.-Si.");
                        System.out.println("2.-No.");
                        repetir = false;
                        int opcion = sc.nextInt();
                        switch (opcion) {
                            case 1:
                                try {
                                    vendedor.venderCoche(coche, coche.getCliente());
                                    coche.getCliente().getReservados().remove(matricula);
                                    coche.getCliente().agregarCocheComprado(coche);
                                    System.out.println("Venta realizada con éxito.");
                                } catch (ExceptionParametrosInvalidos e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                System.out.println("Cancelando operación...");
                                break;
                            default:
                                System.out.println("Opción incorrecta");
                                repetir = true;
                        }
                    } while (repetir);
                } else {
                    System.out.println("Listado de clientes.");
                    System.out.println(concesionario.verListaClientes());
                    System.out.println("Indica el DNI del cliente para realizar la venta:");
                    String dni = sc.next();
                    do {
                        try {
                            concesionario.existeCliente(dni);
                            repetir = false;
                        } catch (NullClienteException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Indica un DNI de la lista o escriba 'salir' para volver.");
                            dni = sc.next();
                            if (!dni.equals("salir")) {
                                repetir = true;
                            } else if (dni.equals("salir")) {
                                repetir = false;
                            }
                        }
                    } while (repetir);
                    Cliente cliente = concesionario.getListadoClientes().get(dni);
                    if (cliente != null) {
                        try {
                            vendedor.venderCoche(coche, cliente);
                            cliente.agregarCocheComprado(coche);
                            coche.setCliente(cliente);
                            System.out.println("Venta realizada con éxito.");
                        } catch (ExceptionParametrosInvalidos e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
    }


    private void reservarCoche(VendedorComision vendedor) {
        Coche coche = null;
        if (concesionario.listaCochesStock().isEmpty()) {
            System.out.println("No hay coches disponibles en el concesionario para reservar.");
        } else if (concesionario.getListadoClientes().isEmpty()) {
            System.out.println("No existen clientes para realizar la operación.");
        } else {
            System.out.println("Listado de coches en Stock:");
            System.out.println(concesionario.verCochesVenta());
            System.out.println("Indica la matrícula del coche que quieres reservar.");
            String matricula = sc.next();
            do {
                try {
                    concesionario.existeCoche(matricula);
                    coche = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
                    if (!concesionario.listaCochesStock().contains(coche)) {
                        throw new ExceptionParametrosInvalidos("El coche no está en venta.");
                    }
                    repetir = false;
                } catch (ExceptionParametrosInvalidos e) {
                    System.out.println(e.getMessage());
                    System.out.println("Indica una matrícula de la lista o escriba 'salir' para volver.");
                    matricula = sc.next();
                    if (!matricula.equals("salir")) {
                        repetir = true;
                    } else if (matricula.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            if (!matricula.equals("salir")) {
                System.out.println("Listado de clientes.");
                System.out.println(concesionario.verListaClientes());
                System.out.println("Indica el DNI del cliente para realizar la reserva:");
                String dni = sc.next();
                do {
                    try {
                        concesionario.existeCliente(dni);
                        repetir = false;
                    } catch (NullClienteException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Indica un DNI de la lista o escriba 'salir' para volver.");
                        dni = sc.next();
                        if (!dni.equals("salir")) {
                            repetir = true;
                        } else if (dni.equals("salir")) {
                            repetir = false;
                        }
                    }
                } while (repetir);
                Cliente cliente = concesionario.getListadoClientes().get(dni);
                if (cliente != null) {
                    try {
                        vendedor.reservarCoche(coche, cliente);
                        cliente.agregarCocheReservado(coche);
                        coche.setCliente(cliente);
                        System.out.println("Reserva realizada con éxito.");
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private void cancelarReserva(VendedorComision vendedor) throws ExceptionParametrosInvalidos {
        Cliente cliente = null;
        if (concesionario.listaCochesReservados().isEmpty()) {
            System.out.println("No hay coches reservados por ningún cliente.");
        } else {
            System.out.println("Listado de clientes.");
            System.out.println(concesionario.verListaClientes());
            System.out.println("Indica el DNI del cliente para ver los coches reservados.");
            String dni = sc.next();
            do {
                try {
                    concesionario.existeCliente(dni);
                    cliente = concesionario.getListadoClientes().get(dni);
                    repetir = false;
                } catch (NullClienteException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Indica un DNI de la lista o escribe 'salir' para volver.");
                    dni = sc.next();
                    if (!dni.equals("salir")) {
                        repetir = true;
                    } else if (dni.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            if (cliente != null) {
                if (cliente.getReservados().isEmpty())
                    throw new ExceptionParametrosInvalidos("El cliente no tiene ningún coche reservado.");
                System.out.println("Listado de coches reservados.");
                System.out.println(concesionario.reservasCliente(cliente));
                System.out.println("Indique la matrícula del coche que desea cancelar la reserva:");
                String matricula = sc.next();
                do {
                    try {
                        concesionario.existeCoche(matricula);
                        Coche coche = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
                        if (!cliente.getReservados().containsKey(matricula))
                            throw new ExceptionParametrosInvalidos("El coche no está reservado por este cliente.");
                        vendedor.cancelarReserva(coche);
                        cliente.getReservados().remove(matricula);
                        System.out.println("Reserva cancelada con éxito.");
                        repetir = false;
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                        System.out.println("Indique una matrícula de la lista o escriba 'salir' para volver.");
                        matricula = sc.next();
                        if (!matricula.equals("salir")) {
                            repetir = true;
                        } else if (matricula.equals("salir")) {
                            repetir = false;
                        }
                    }
                } while (repetir);
            }
        }
    }

    private String consultarReservas() {
        if (concesionario.getListadoClientes().isEmpty()) {
            System.out.println("No hay clientes dados de alta.");
        } else {
            System.out.println("Listado de clientes.");
            System.out.println(concesionario.verListaClientes());
            System.out.println("Indica el DNI del cliente a consultar:");
            String dni = sc.next();
            do {
                try {
                    concesionario.existeCliente(dni);
                    repetir = false;
                } catch (NullClienteException e) {
                    System.out.println(e.getMessage());
                    System.out.println("El DNI indicado no está en la lista. Introduzca un DNI de la lista o escriba 'salir para volver.");
                    dni = sc.next();
                    if (!dni.equals("salir")) {
                        repetir = true;
                    } else if (dni.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            Cliente cliente = concesionario.getListadoClientes().get(dni);
            if (cliente != null) {
                if (cliente.getReservados().isEmpty()) {
                    System.out.println("No hay reservas para el cliente " + cliente.getInfo());
                } else {
                    String datos = cliente.getInfo() + concesionario.verCochesReservadosCliente(dni);
                    return datos;
                }
            }
        }
        return "Cancelando operación...";
    }

    private void altaVendedorComision() throws DuplicadoException {
        System.out.println("Indica los siguientes datos para dar de alta un vendedor:");
        System.out.println("DNI:");
        String dni = sc.next();
        if (concesionario.getListadoVendedores().containsKey(dni))
            throw new DuplicadoException();
        System.out.println("Nombre:");
        String nombre = sc.next();
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        try {
            concesionario.addVendedorComision(nombre, direccion, dni, telefono);
            System.out.println("El vendedor se ha dado de alta con éxito.");
        } catch (ExceptionParametrosInvalidos e) {
            System.out.println(e.getMessage());
        }
    }

    private void bajaVendedorComision() {
        if (concesionario.getListadoVendedores().isEmpty()) {
            System.out.println("No existen vendedores.");
        } else {
            System.out.println("Listado de vendedores disponibles.");
            System.out.println(concesionario.verListaVendedores());
            System.out.println("Indica el DNI del vendedor a dar de baja:");
            String dni = sc.next();
            do {
                try {
                    concesionario.existeVendedor(dni);
                    repetir = false;
                } catch (NullVendedorException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Introduzca un DNI de la lista o escriba 'salir' para volver.");
                    dni = sc.next();
                    if (!dni.equals("salir")) {
                        repetir = true;
                    } else if (dni.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            if (!dni.equals("salir")) {
                do {
                    System.out.println("¿Estas seguro de querer dar de baja a este vendedor?");
                    System.out.println("1.-Sí");
                    System.out.println("2.-No");
                    repetir = false;
                    int opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            try {
                                concesionario.deleteVendedorComision(dni);
                                System.out.println("Se ha dado de baja el vendedor con DNI: '" + dni + "'.");
                            } catch (ExceptionParametrosInvalidos e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            System.out.println("No se da de baja.");
                            break;
                        default:
                            System.out.println("Opción incorrecta.");
                            repetir = true;
                    }
                } while (repetir);
            }
        }
    }

    private void modificarVendedorComision() {
        if (concesionario.getListadoVendedores().isEmpty()) {
            System.out.println("No existen vendedores.");
        } else {
            System.out.println("Listado de vendedores disponibles.");
            System.out.println(concesionario.verListaVendedores());
            System.out.println("Indica el DNI del vendedor a modificar:");
            String dni = sc.next();
            do {
                try {
                    concesionario.existeVendedor(dni);
                    repetir = false;
                } catch (NullVendedorException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Introduzca un DNI de la lista o escriba 'salir' para volver.");
                    dni = sc.next();
                    if (!dni.equals("salir")) {
                        repetir = true;
                    } else if (dni.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            VendedorComision vendedor = concesionario.getListadoVendedores().get(dni);
            if (vendedor != null) {
                System.out.println("Introduzca de nuevo los datos del vendedor con sus modificaciones.");
                System.out.println("Nombre original: " + vendedor.getNombre());
                System.out.println("Nombre nuevo:");
                String nombre = sc.next();
                System.out.println("Dirección original: " + vendedor.getDireccion());
                System.out.println("Dirección nueva: ");
                String direccion = sc.next();
                System.out.println("Teléfono original: " + vendedor.getTelefono());
                System.out.println("Teléfono nuevo: ");
                int telefono = sc.nextInt();
                try {
                    concesionario.changeVendedorComision(nombre, direccion, dni, telefono);
                    System.out.println("Los nuevos datos del vendedor son:");
                    System.out.println(vendedor.getInfo());
                } catch (ExceptionParametrosInvalidos e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void altaExposicion() throws DuplicadoException {
        System.out.println("Introduzca los siguientes datos para dar de alta una exposición:");
        System.out.println("Nº exposición:");
        int numExpo = sc.nextInt();
        if (concesionario.getListadoExposiciones().containsKey(numExpo)) {
            throw new DuplicadoException();
        }
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        try {
            concesionario.addExposicion(numExpo, direccion, telefono);
            System.out.println("La exposición se ha dado de alta.");
        } catch (ExceptionParametrosInvalidos e) {
            System.out.println(e.getMessage());
        }
    }

    private void bajaExposicion() {
        if (concesionario.getListadoExposiciones().isEmpty()) {
            System.out.println("No existen exposiciones.");
        } else {
            System.out.println("Listado de exposiciones disponibles.");
            System.out.println(concesionario.verListaExposiciones());
            System.out.println("Indica el número de exposición que desea dar de baja de la lista:");
            int numExpo = sc.nextInt();
            do {
                try {
                    concesionario.existeExposicion(numExpo);
                    repetir = false;
                } catch (NullExposicionException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Introduzca un número de la lista o indique 0 para volver.");
                    numExpo = sc.nextInt();
                    if (numExpo != 0) {
                        repetir = true;
                    } else if (numExpo == 0) {
                        repetir = false;
                    }
                }
            } while (repetir);
            if (numExpo != 0) {
                do {
                    System.out.println("¿Estas seguro de querer dar de baja a esta exposición?");
                    System.out.println("1.-Sí");
                    System.out.println("2.-No");
                    repetir = false;
                    int opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            concesionario.deleteExposicion(numExpo);
                            System.out.println("Se ha dado de baja la exposición nº '" + numExpo + "'.");
                            break;
                        case 2:
                            System.out.println("No se da de baja.");
                            break;
                        default:
                            System.out.println("Opción incorrecta.");
                            repetir = true;
                    }
                } while (repetir);
            }
        }
    }

    private void modificarExposicion() {
        if (concesionario.getListadoExposiciones().isEmpty()) {
            System.out.println("No existen exposiciones.");
        } else {
            System.out.println("Listado de exposiciones disponibles.");
            System.out.println(concesionario.verListaExposiciones());
            System.out.println("Indica el número de exposición que desea modificar de la lista:");
            int numExpo = sc.nextInt();
            do {
                try {
                    concesionario.existeExposicion(numExpo);
                    repetir = false;
                } catch (NullExposicionException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Introduzca un número de la lista o indique 0 para volver.");
                    numExpo = sc.nextInt();
                    if (numExpo != 0) {
                        repetir = true;
                    } else if (numExpo == 0) {
                        repetir = false;
                    }
                }
            } while (repetir);
            Exposicion exposicion = concesionario.getListadoExposiciones().get(numExpo);
            if (exposicion != null) {
                System.out.println("Introduzca de nuevo los datos de la exposición con sus modificaciones.");
                System.out.println("Dirección original: " + exposicion.getDireccion());
                System.out.println("Dirección nueva: ");
                String direccion = sc.next();
                System.out.println("Teléfono original: " + exposicion.getTelefono());
                System.out.println("Teléfono nuevo: ");
                int telefono = sc.nextInt();
                try {
                    concesionario.changeExposicion(numExpo, direccion, telefono);
                    System.out.println("Los nuevos datos de la exposición son:");
                    System.out.println(exposicion.getInfo());
                } catch (ExceptionParametrosInvalidos e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void verDatosExposicion() {
        if (concesionario.getListadoExposiciones().isEmpty()) {
            System.out.println("No existen exposiciones.");
        } else {
            System.out.println("Las exposiciones disponibles son las siguientes: ");
            System.out.println(concesionario.verListaExposiciones());
            System.out.println("Indica el número de exposición para ver sus datos: ");
            int numExpo = sc.nextInt();
            do {
                try {
                    concesionario.existeExposicion(numExpo);
                    System.out.println("Los datos de la exposición son: ");
                    if (concesionario.listadoCochesExposicion(numExpo).isEmpty()) {
                        System.out.println(concesionario.verExpo(numExpo));
                        System.out.println("No hay coches en esta exposición.");
                    } else {
                        System.out.println(concesionario.verExpo(numExpo) + "\n" + concesionario.verCochesExpo(numExpo));
                    }
                    repetir = false;
                } catch (NullExposicionException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Introduzca un número de la lista o indique 0 para volver.");
                    numExpo = sc.nextInt();
                    if (numExpo != 0) {
                        repetir = true;
                    } else if (numExpo == 0) {
                        repetir = false;
                    }
                }
            } while (repetir);
        }
    }


    private void menuCambiarCocheExposicion() {
        if (concesionario.listaCochesStock().isEmpty()) {
            System.out.println("No hay coches disponibles para cambiar de exposición.");
        } else {
            Coche coche = null;
            System.out.println("Listado de coches disponibles.");
            System.out.println(concesionario.verCochesVenta());
            System.out.println("Indica la matrícula del coche que desea cambiar:");
            String matricula = sc.next();
            do {
                try {
                    concesionario.existeCoche(matricula);
                    coche = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
                    if (!concesionario.listaCochesStock().contains(coche)) {
                        throw new NullCocheException();
                    }
                    repetir = false;
                } catch (NullCocheException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Indica una matrícula correcta o escribe 'salir' para cancelar");
                    matricula = sc.next();
                    if (!matricula.equals("salir")) {
                        repetir = true;
                    } else if (matricula.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            if (!matricula.equals("salir")) {
                System.out.println("Indica la exposición de destino:");
                System.out.println(concesionario.verListaExposiciones());
                int numExpo = sc.nextInt();
                do {
                    try {
                        concesionario.existeExposicion(numExpo);
                        repetir = false;
                    } catch (NullExposicionException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Introduzca un número de la lista o indique 0 para volver.");
                        numExpo = sc.nextInt();
                        if (numExpo != 0) {
                            repetir = true;
                        } else if (numExpo == 0) {
                            repetir = false;
                        }
                    }
                } while (repetir);
                if (numExpo != 0) {
                    try {
                        concesionario.cambiarCocheExposicion(matricula, numExpo);
                        System.out.println("Cambio realizado con éxito.");
                    } catch (ExceptionParametrosInvalidos e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }


    private void altaCoche() throws DuplicadoException {
        if (concesionario.getListadoExposiciones().isEmpty()) {
            System.out.println("Se necesita una exposición para dar de alta un coche.");
        } else {
            System.out.println("Introduzca los siguientes datos para dar de alta un coche.");
            System.out.println("Matrícula:");
            String matricula = sc.next();
            if (concesionario.getListadoCochesTotalesDefinitivo().containsKey(matricula)) {
                throw new DuplicadoException();
            }
            System.out.println("Marca:");
            String marca = sc.next();
            System.out.println("Modelo:");
            String modelo = sc.next();
            System.out.println("Precio de compra:");
            double compra = sc.nextDouble();
            System.out.println("Precio de venta:");
            double venta = sc.nextDouble();
            TipoCoche t = null;
            do {
                System.out.println("Elige el tipo:");
                System.out.println("1.-Industrial.");
                System.out.println("2.-Todoterreno.");
                System.out.println("3.-Turismo.");
                repetir = false;
                int tipo = sc.nextInt();
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
                        repetir = true;
                }
            } while (repetir);
            System.out.println("Nº exposición: ");
            int numExpo = sc.nextInt();
            if (!concesionario.getListadoExposiciones().containsKey(numExpo))
                throw new NullExposicionException();
            Exposicion exposicion = concesionario.getListadoExposiciones().get(numExpo);
            try {
                concesionario.addCoche(marca, modelo, matricula, compra, venta, t, exposicion);
                System.out.println("El coche se ha dado de alta con éxito.");
            } catch (ExceptionParametrosInvalidos e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void bajaCoche() {
        if (concesionario.getListadoCochesTotalesDefinitivo().isEmpty()) {
            System.out.println("No existen coches.");
        } else {
            System.out.println("Los coches disponibles son los siguientes: ");
            System.out.println(concesionario.verCochesConcesionario());
            System.out.println("Indica la matrícula del coche para darlo de baja: ");
            String matricula = sc.next();
            do {
                try {
                    concesionario.existeCoche(matricula);
                    repetir = false;
                } catch (NullCocheException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Indica un coche de la lista o escribe 'salir' para volver.");
                    matricula = sc.next();
                    if (!matricula.equals("salir")) {
                        repetir = true;
                    } else if (matricula.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            if (!matricula.equals("salir")) {
                do {
                    System.out.println("¿Estas seguro de querer dar de baja a este coche?");
                    System.out.println("1.-Sí");
                    System.out.println("2.-No");
                    repetir = false;
                    int opcion = sc.nextInt();
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
                            repetir = true;
                    }
                } while (repetir);
            }
        }
    }

    private void modificarCoche() {
        if (concesionario.getListadoCochesTotalesDefinitivo().isEmpty()) {
            System.out.println("No existen coches.");
        } else {
            TipoCoche t = null;
            System.out.println("Los coches disponibles son los siguientes: ");
            System.out.println(concesionario.verCochesConcesionario());
            System.out.println("Indica la matrícula del coche que quieres modificar: ");
            String matricula = sc.next();
            do {
                try {
                    concesionario.existeCoche(matricula);
                    repetir = false;
                } catch (NullCocheException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Indica un coche de la lista o escribe 'salir' para volver.");
                    matricula = sc.next();
                    if (!matricula.equals("salir")) {
                        repetir = true;
                    } else if (matricula.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            Coche coche = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
            if (coche != null) {
                System.out.println("Introduzca de nuevo los datos del coche con sus modificaciones.");
                System.out.println("Marca:");
                String marca = sc.next();
                System.out.println("Modelo:");
                String modelo = sc.next();
                System.out.println("Precio de compra:");
                double compra = sc.nextDouble();
                System.out.println("Precio de venta:");
                double venta = sc.nextDouble();
                do {
                    System.out.println("Elige el tipo:");
                    System.out.println("1.-Industrial.");
                    System.out.println("2.-Todoterreno.");
                    System.out.println("3.-Turismo.");
                    repetir = false;
                    int tipo = sc.nextInt();
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
                            repetir = true;
                    }
                } while (repetir);
                System.out.println("Nº exposición: ");
                int numExpo = sc.nextInt();
                try {
                    concesionario.existeExposicion(numExpo);
                    Exposicion exposicion = concesionario.getListadoExposiciones().get(numExpo);
                    concesionario.changeCoche(marca, modelo, matricula, compra, venta, t, exposicion);
                    System.out.println("Los nuevos datos del coche con:");
                    System.out.println(coche.getInfo());
                } catch (ExceptionParametrosInvalidos e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void altaCliente() throws DuplicadoException {
        System.out.println("Introduzca los siguientes datos para dar de alta un cliente.");
        System.out.println("DNI:");
        String dni = sc.next();
        if (concesionario.getListadoClientes().containsKey(dni))
            throw new DuplicadoException();
        System.out.println("Nombre:");
        String nombre = sc.next();
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        try {
            concesionario.addCliente(dni, nombre, direccion, telefono);
            System.out.println("El cliente se ha dado de alta con éxito.");
        } catch (ExceptionParametrosInvalidos e) {
            System.out.println(e.getMessage());
        }
    }

    private void bajaCliente() {
        if (concesionario.getListadoClientes().isEmpty()) {
            System.out.println("No existen clientes.");
        } else {
            System.out.println("Listado de clientes disponibles.");
            System.out.println(concesionario.verListaClientes());
            System.out.println("Indica el DNI del cliente a dar de baja:");
            String dni = sc.next();
            do {
                try {
                    concesionario.existeCliente(dni);
                    repetir = false;
                } catch (NullClienteException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Introduzca un DNI de la lista o escriba 'salir' para volver.");
                    dni = sc.next();
                    if (!dni.equals("salir")) {
                        repetir = true;
                    } else if (dni.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            if (!dni.equals("salir")) {
                do {
                    System.out.println("¿Estas seguro de querer dar de baja a este cliente?");
                    System.out.println("1.-Sí");
                    System.out.println("2.-No");
                    repetir = false;
                    int opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            try {
                                concesionario.deleteCliente(dni);
                                System.out.println("Se ha dado de baja el cliente con DNI: '" + dni + "'.");
                            } catch (ExceptionParametrosInvalidos e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            System.out.println("No se da de baja.");
                            break;
                        default:
                            System.out.println("Opción incorrecta.");
                            repetir = true;
                    }
                } while (repetir);
            }
        }
    }

    private void modificarCliente() {
        if (concesionario.getListadoClientes().isEmpty()) {
            System.out.println("No existen clientes.");
        } else {
            System.out.println("Listado de clientes disponibles.");
            System.out.println(concesionario.verListaClientes());
            System.out.println("Indica el DNI del cliente a modificar:");
            String dni = sc.next();
            do {
                try {
                    concesionario.existeCliente(dni);
                    repetir = false;
                } catch (NullClienteException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Introduzca un DNI de la lista o escriba 'salir' para volver.");
                    dni = sc.next();
                    if (!dni.equals("salir")) {
                        repetir = true;
                    } else if (dni.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            Cliente cliente = concesionario.getListadoClientes().get(dni);
            if (cliente != null) {
                System.out.println("Introduzca de nuevo los datos del cliente con sus modificaciones.");
                System.out.println("Nombre original: " + cliente.getNombre());
                System.out.println("Nombre nuevo:");
                String nombre = sc.next();
                System.out.println("Dirección original: " + cliente.getDireccion());
                System.out.println("Dirección nueva:");
                String direccion = sc.next();
                System.out.println("Teléfono original: " + cliente.getTelefono());
                System.out.println("Teléfono nuevo:");
                int telefono = sc.nextInt();
                try {
                    concesionario.changeCliente(nombre, direccion, dni, telefono);
                    System.out.println("Los nuevos datos del vendedor son:");
                    System.out.println(cliente.getInfo());
                } catch (ExceptionParametrosInvalidos e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void altaMecanico() throws DuplicadoException {
        System.out.println("Introduzca los siguientes datos para dar de alta un mecánico.");
        System.out.println("DNI:");
        String dni = sc.next();
        if (concesionario.getListadoMecanicos().containsKey(dni))
            throw new DuplicadoException();
        System.out.println("Nombre:");
        String nombre = sc.next();
        System.out.println("Dirección:");
        String direccion = sc.next();
        System.out.println("Teléfono:");
        int telefono = sc.nextInt();
        try {
            concesionario.addMecanico(dni, nombre, direccion, telefono);
            System.out.println("El mecánico se ha dado de alta con éxito.");
        } catch (ExceptionParametrosInvalidos e) {
            System.out.println(e.getMessage());
        }
    }

    private void bajaMecanico() {
        if (concesionario.getListadoMecanicos().isEmpty()) {
            System.out.println("No existen mecánicos.");
        } else {
            System.out.println("Listado de mecánicos disponibles.");
            System.out.println(concesionario.verListaMecanicos());
            System.out.println("Indica el DNI del mecánico a dar de baja:");
            String dni = sc.next();
            do {
                try {
                    concesionario.existeMecanico(dni);
                    repetir = false;
                } catch (NullMecanicoException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Introduzca un DNI de la lista o escriba 'salir' para volver.");
                    dni = sc.next();
                    if (!dni.equals("salir")) {
                        repetir = true;
                    } else if (dni.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            if (!dni.equals("salir")) {
                do {
                    System.out.println("¿Estas seguro de querer dar de baja a este mecánico?");
                    System.out.println("1.-Sí");
                    System.out.println("2.-No");
                    repetir = false;
                    int opcion;
                    opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            concesionario.deleteMecanico(dni);
                            System.out.println("Se ha dado de baja el mecánico con DNI: '" + dni + "'.");
                            break;
                        case 2:
                            System.out.println("No se da de baja.");
                            break;
                        default:
                            System.out.println("Opción incorrecta.");
                            repetir = true;
                    }
                } while (repetir);
            }
        }
    }

    private void modificarMecanico() {
        if (concesionario.getListadoMecanicos().isEmpty()) {
            System.out.println("No existen mecánicos.");
        } else {
            System.out.println("Listado de mecánicos disponibles.");
            System.out.println(concesionario.verListaMecanicos());
            System.out.println("Indica el DNI del mecánico a modificar:");
            String dni = sc.next();
            do {
                try {
                    concesionario.existeMecanico(dni);
                    repetir = false;
                } catch (NullMecanicoException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Introduzca un DNI de la lista o escriba 'salir' para volver.");
                    dni = sc.next();
                    if (!dni.equals("salir")) {
                        repetir = true;
                    } else if (dni.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            Mecanico mecanico = concesionario.getListadoMecanicos().get(dni);
            if (mecanico != null) {
                System.out.println("Introduzca de nuevo los datos del mecánico con sus modificaciones.");
                System.out.println("Nombre original: " + mecanico.getNombre());
                System.out.println("Nombre nuevo:");
                String nombre = sc.next();
                System.out.println("Dirección original: " + mecanico.getDireccion());
                System.out.println("Dirección nueva:");
                String direccion = sc.next();
                System.out.println("Teléfono original: " + mecanico.getTelefono());
                System.out.println("Teléfono nuevo:");
                int telefono = sc.nextInt();
                try {
                    concesionario.changeMecanico(nombre, direccion, dni, telefono);
                    System.out.println("Los nuevos datos del mecánico son:");
                    System.out.println(mecanico.getInfo());
                } catch (ExceptionParametrosInvalidos e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void mandarRepararCoche() {
        if (concesionario.getListadoMecanicos().isEmpty()) {
            System.out.println("No se puede crear una reparación sin un mecánico.");
        } else if (concesionario.listaCochesStock().isEmpty()) {
            System.out.println("No hay coches disponibles para reparar.");
        } else {
            System.out.println("Listado de coches que puede reparar.");
            System.out.println(concesionario.verCochesVenta());
            System.out.println("Indica la matrícula del coche que quieres reparar:");
            String matricula = sc.next();
            do {
                try {
                    concesionario.existeCoche(matricula);
                    repetir = false;
                } catch (NullCocheException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Introduzca una matrícula de la lista o escriba 'salir' para volver.");
                    matricula = sc.next();
                    if (!matricula.equals("salir")) {
                        repetir = true;
                    } else if (matricula.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            if (!matricula.equals("salir")) {
                TipoReparacion t = null;
                do {
                    System.out.println("Indica el tipo de reparación a realizar: ");
                    System.out.println("1.-Chapa.");
                    System.out.println("2.-Eléctrica.");
                    System.out.println("3.-Mecánica.");
                    System.out.println("4.-Revisión.");
                    repetir = false;
                    int tipo = sc.nextInt();
                    switch (tipo) {
                        case 1:
                            t = TipoReparacion.chapa;
                            break;
                        case 2:
                            t = TipoReparacion.electrica;
                            break;
                        case 3:
                            t = TipoReparacion.mecanica;
                            break;
                        case 4:
                            t = TipoReparacion.revision;
                            break;
                        default:
                            System.out.println("Tipo erróneo");
                            repetir = true;
                    }
                } while (repetir);
                try {
                    concesionario.cocheAReparar(matricula, t);
                    System.out.println("Reparación ordenada con éxito.");
                } catch (ExceptionParametrosInvalidos e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void solucionarReparacion() {
        if (concesionario.listaCochesReparacion().isEmpty()) {
            System.out.println("No hay coches en el taller reparándose.");
        } else {
            System.out.println("Listado de coches del taller reparándose.");
            System.out.println(concesionario.verCochesReparacion());
            System.out.println("Indica la matrícula del coche reparado:");
            String matricula = sc.next();
            do {
                try {
                    concesionario.existeCoche(matricula);
                    Coche coche = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
                    if (!concesionario.listaCochesReparacion().contains(coche)) {
                        throw new ExceptionParametrosInvalidos("El coche no está en reparación.");
                    }
                    repetir = false;
                } catch (ExceptionParametrosInvalidos e) {
                    System.out.println(e.getMessage());
                    System.out.println("Introduzca una matrícula de la lista o escriba 'salir' para volver.");
                    matricula = sc.next();
                    if (!matricula.equals("salir")) {
                        repetir = true;
                    } else if (matricula.equals("salir")) {
                        repetir = false;
                    }
                }
            } while (repetir);
            if (!matricula.equals("salir")) {
                concesionario.cocheReparado(matricula);
                System.out.println("Reparación solucionada con éxito.");
            }
        }
    }

    public void consultarReparaciones(String matricula) {
        Coche coche = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
        coche.getReparaciones().sort(
                (reparacion1, reparacion2) -> {
                    return reparacion1.compareTo(reparacion2);
                }
        );
        if (coche.getReparaciones().isEmpty()) {
            System.out.println("No se ha realizado ninguna reparación.");
        } else {
            for (Reparacion r : coche.getReparaciones()) {
                if (r.isResuelta()) {
                    System.out.println(r.getInfo());
                }
            }
        }
    }

    public void menu() {
        while (!salir) {
            System.out.println("¿Quién eres?");
            System.out.println("1.-Director comercial.");
            System.out.println("2.-Vendedor.");
            System.out.println("3.-Mecánico.");
            System.out.println("4.-Cliente.");
            System.out.println("9.-Salir del programa.");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    try {
                        menuDirectorComercial();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        menuVendedoresComision();
                    } catch (NullVendedorException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        menuMecanico();
                    } catch (NullMecanicoException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 4:
                    if (concesionario.listaCochesStock().isEmpty()) {
                        System.out.println("No hay coches disponibles para comprar.");
                    } else {
                        System.out.println("Listado de coches disponibles para comprar: ");
                        System.out.println(concesionario.verCochesVenta());
                    }
                    break;
                case 9:
                    System.out.println("Saliendo.....");
                    salir = true;
                    break;
                default:
                    System.out.println("Indica una opción correcta.");
            }
        }
    }

    private void menuDirectorComercial() {
        if (d1 == null) {
            System.out.println("DNI: ");
            String dni = sc.next();
            System.out.println("Nombre: ");
            String nombre = sc.next();
            System.out.println("Dirección: ");
            String direccion = sc.next();
            System.out.println("Teléfono: ");
            int telefono = sc.nextInt();
            try {
                d1 = new DirectorComercial(nombre, direccion, dni, telefono);
            } catch (ExceptionParametrosInvalidos e) {
                System.out.println(e.getMessage());
            }
        }
        if (d1 != null) {
            while (!salir) {
                System.out.println("Bienvenido " + d1.getNombre());
                System.out.println("Elige que deseas hacer.");
                System.out.println("1.-Gestión coches.");
                System.out.println("2.-Gestión exposiciones.");
                System.out.println("3.-Gestión vendedores.");
                System.out.println("4.-Gestión clientes.");
                System.out.println("5.-Gestión mecánicos.");
                System.out.println("6.-Consultas.");
                System.out.println("9.-Volver.");
                int opcion = sc.nextInt();
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
                        gestionMecanicos(sc);
                        break;
                    case 6:
                        try {
                            consultas(sc);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 9:
                        menu();
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción incorrecta.");
                }
            }
        }
    }

    private void consultas(Scanner sc) {
        while (!salir) {
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
                    if (concesionario.listaCochesStock().isEmpty()) {
                        System.out.println("No hay coches en venta.");
                    } else {
                        System.out.println(concesionario.verCochesVenta());
                    }
                    break;
                case 2:
                    if (concesionario.listaCochesReservados().isEmpty()) {
                        System.out.println("No hay coches reservados.");
                    } else {
                        System.out.println(concesionario.verCochesReservados());
                    }
                    break;
                case 3:
                    if (concesionario.listaCochesReparacion().isEmpty()) {
                        System.out.println("No hay coches reparándose.");
                    } else {
                        System.out.println(concesionario.verCochesReparacion());
                    }
                    break;
                case 4:
                    if (concesionario.getListadoVendedores().isEmpty()) {
                        System.out.println("No hay vendedores dados de alta.");
                    } else {
                        System.out.println("Listado de vendedores.");
                        System.out.println(concesionario.verListaVendedores());
                        System.out.println("Indica el DNI del vendedor a consultar: ");
                        String dni = sc.next();
                        do {
                            try {
                                concesionario.existeVendedor(dni);
                                repetir = false;
                            } catch (NullVendedorException e) {
                                System.out.println(e.getMessage());
                                System.out.println("Indique un DNI de la lista o escriba 'salir' para volver.");
                                dni = sc.next();
                                if (!dni.equals("salir")) {
                                    repetir = true;
                                } else if (dni.equals("salir")) {
                                    repetir = false;
                                }
                            }
                        } while (repetir);
                        VendedorComision vendedor = concesionario.getListadoVendedores().get(dni);
                        if (vendedor != null) {
                            System.out.println("El listado de coches vendidos por " + vendedor.getNombre() + " es:");
                            for (Coche coche : vendedor.getCochesVendidos().values()) {
                                System.out.println(coche.getInfo());
                            }
                            int sueldo = vendedor.getCochesVendidos().size() * 200;
                            System.out.println("El sueldo es: " + sueldo + " €");
                        }
                    }
                    break;
                case 5:
                    System.out.println(consultarReservas());
                    break;
                case 6:
                    if (concesionario.listaCochesVendidos().isEmpty()) {
                        System.out.println("No hay coches vendidos.");
                    } else {
                        System.out.println("Elige el coche que quieres consultar de la lista: ");
                        System.out.println(concesionario.verCochesVendidos());
                        String matricula = sc.next();
                        do {
                            try {
                                concesionario.existeCoche(matricula);
                                Coche coche = concesionario.getListadoCochesTotalesDefinitivo().get(matricula);
                                if (!concesionario.listaCochesVendidos().contains(coche)) {
                                    throw new ExceptionParametrosInvalidos("El coche no está vendido.");
                                }
                                repetir = false;
                            } catch (ExceptionParametrosInvalidos e) {
                                System.out.println(e.getMessage());
                                System.out.println("Indica una matrícula de la lista o escribe 'salir' para volver");
                                if (!matricula.equals("salir")) {
                                    repetir = true;
                                } else if (matricula.equals("salir")) {
                                    repetir = false;
                                }
                            }
                        } while (repetir);
                        if (!matricula.equals("salir")) {
                            System.out.println(concesionario.mostrarCliente(matricula));
                        }
                    }
                    break;
                case 9:
                    menuDirectorComercial();
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }

    private void gestionClientes(Scanner sc) {
        while (!salir) {
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
                    } catch (DuplicadoException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        bajaCliente();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        modificarCliente();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    if (concesionario.getListadoClientes().isEmpty()) {
                        System.out.println("No hay clientes dados de alta.");
                    } else {
                        System.out.println(concesionario.verListaClientes());
                    }
                    break;
                case 9:
                    menuDirectorComercial();
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }

    private void gestionMecanicos(Scanner sc) {
        while (!salir) {
            System.out.println("1.-Dar de alta un mecánico.");
            System.out.println("2.-Dar de baja un mecánico.");
            System.out.println("3.-Modificar un mecánico.");
            System.out.println("4.-Visualizar los mecánicos.");
            System.out.println("5.-Reparar un coche.");
            System.out.println("6.-Solucionar reparación.");
            System.out.println("7.-Consultar listado reparaciones realizadas.");
            System.out.println("9.-Volver.");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        altaMecanico();
                    } catch (DuplicadoException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        bajaMecanico();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        modificarMecanico();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    if (concesionario.getListadoMecanicos().isEmpty()) {
                        System.out.println("No hay mecánicos dados de alta");
                    } else {
                        System.out.println(concesionario.verListaMecanicos());
                    }
                    break;
                case 5:
                    try {
                        mandarRepararCoche();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        solucionarReparacion();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    if (concesionario.getListadoCochesTotalesDefinitivo().isEmpty()) {
                        System.out.println("No hay coches en el concesionario para realizar la consulta.");
                    } else {
                        System.out.println("Indica la matrícula del coche a consultar:");
                        String matricula = sc.next();
                        try {
                            concesionario.existeCoche(matricula);
                            consultarReparaciones(matricula);
                        } catch (NullCocheException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 9:
                    menuDirectorComercial();
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }

    private void gestionVendedores(Scanner sc) {
        while (!salir) {
            System.out.println("1.-Dar de alta un vendedor.");
            System.out.println("2.-Dar de baja un vendedor.");
            System.out.println("3.-Modificar un vendedor.");
            System.out.println("4.-Visualizar los vendedores.");
            System.out.println("5.-Vender un coche.");
            System.out.println("6.-Reservar un coche.");
            System.out.println("7.-Cancelar reserva de un coche.");
            System.out.println("9.-Volver.");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        altaVendedorComision();
                    } catch (DuplicadoException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        bajaVendedorComision();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        modificarVendedorComision();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    if (concesionario.getListadoVendedores().isEmpty()) {
                        System.out.println("No hay vendedores dados de alta.");
                    } else {
                        System.out.println(concesionario.verListaVendedores());
                    }
                    break;
                case 5:
                    if (concesionario.getListadoVendedores().isEmpty()) {
                        System.out.println("No existen vendedores dados de alta.");
                    } else {
                        System.out.println("Listado de vendedores.");
                        System.out.println(concesionario.verListaVendedores());
                        System.out.println("Indica el DNI del vendedor que va a realizar la operación.");
                        String dni = sc.next();
                        do {
                            try {
                                concesionario.existeVendedor(dni);
                                repetir = false;
                            } catch (NullVendedorException e) {
                                System.out.println(e.getMessage());
                                System.out.println("Indica un DNI correcto o escribe 'salir' para volver.");
                                dni = sc.next();
                                if (!dni.equals("salir")) {
                                    repetir = true;
                                } else if (dni.equals("salir")) {
                                    repetir = false;
                                }

                            }
                        } while (repetir);
                        VendedorComision vendedor = concesionario.getListadoVendedores().get(dni);
                        if (vendedor != null) {
                            try {
                                venderCoche(vendedor);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                case 6:
                    if (concesionario.getListadoVendedores().isEmpty()) {
                        System.out.println("No existen vendedores dados de alta.");
                    } else {
                        System.out.println("Listado de vendedores.");
                        System.out.println(concesionario.verListaVendedores());
                        System.out.println("Indica el DNI del vendedor que va a realizar la operación.");
                        String dni = sc.next();
                        do {
                            try {
                                concesionario.existeVendedor(dni);
                                repetir = false;
                            } catch (NullVendedorException e) {
                                System.out.println(e.getMessage());
                                System.out.println("Introduzca un DNI de la lista o escriba 'salir' para volver.");
                                dni = sc.next();
                                if (!dni.equals("salir")) {
                                    repetir = true;
                                } else if (dni.equals("salir")) {
                                    repetir = false;
                                }
                            }
                        } while (repetir);
                        VendedorComision vendedor = concesionario.getListadoVendedores().get(dni);
                        if (vendedor != null) {
                            try {
                                reservarCoche(vendedor);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                case 7:
                    if (concesionario.getListadoVendedores().isEmpty()) {
                        System.out.println("No existen vendedores dados de alta.");
                    } else {
                        System.out.println("Listado de vendedores.");
                        System.out.println(concesionario.verListaVendedores());
                        System.out.println("Indica el DNI del vendedor que va a realizar la operación.");
                        String dni = sc.next();
                        do {
                            try {
                                concesionario.existeVendedor(dni);
                                repetir = false;
                            } catch (NullVendedorException e) {
                                System.out.println(e.getMessage());
                                System.out.println("Introduzca un DNI de la lista o escriba 'salir' para volver.");
                                dni = sc.next();
                                if (!dni.equals("salir")) {
                                    repetir = true;
                                } else if (dni.equals("salir")) {
                                    repetir = false;
                                }
                            }
                        } while (repetir);
                        VendedorComision vendedor = concesionario.getListadoVendedores().get(dni);
                        if (vendedor != null) {
                            try {
                                cancelarReserva(vendedor);
                            } catch (ExceptionParametrosInvalidos e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                    break;
                case 9:
                    menuDirectorComercial();
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }

    private void gestionExposiciones(Scanner sc) {
        while (!salir) {
            System.out.println("1.-Dar de alta una exposición.");
            System.out.println("2.-Dar de baja una exposición.");
            System.out.println("3.-Modificar una exposición.");
            System.out.println("4.-Visualizar datos de una exposición.");
            System.out.println("5.-Cambiar coche de exposición.");
            System.out.println("9.-Volver.");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        altaExposicion();
                    } catch (DuplicadoException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        bajaExposicion();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        modificarExposicion();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        verDatosExposicion();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        menuCambiarCocheExposicion();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    menuDirectorComercial();
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }

    private void gestionCoches(Scanner sc) {
        while (!salir) {
            System.out.println("1.-Dar de alta un coche.");
            System.out.println("2.-Dar de baja un coche.");
            System.out.println("3.-Modificar un coche.");
            System.out.println("4.-Visualizar los coches");
            System.out.println("9.-Volver.");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    try {
                        altaCoche();
                    } catch (DuplicadoException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        bajaCoche();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        modificarCoche();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    if (concesionario.getListadoCochesTotalesDefinitivo().isEmpty()) {
                        System.out.println("No hay coches en el concesionario.");
                    } else {
                        System.out.println(concesionario.verCochesConcesionario());
                    }
                    break;
                case 9:
                    menuDirectorComercial();
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }

    private void menuVendedoresComision() throws NullVendedorException {
        if (concesionario.getListadoVendedores().isEmpty()) {
            throw new NullVendedorException();
        }
        System.out.println("Indica tu DNI para acceder:");
        String dni = sc.next();
        do {
            try {
                concesionario.existeVendedor(dni);
                repetir = false;
            } catch (NullVendedorException e) {
                System.out.println(e.getMessage());
                System.out.println("Indica un DNI correcto o escribe 'salir' para volver.");
                dni = sc.next();
                if (!dni.equals("salir")) {
                    repetir = true;
                } else if (dni.equals("salir")) {
                    repetir = false;
                }
            }
        } while (repetir);
        VendedorComision vendedor = concesionario.getListadoVendedores().get(dni);
        if (vendedor != null) {
            System.out.println("Bienvenido " + vendedor.getNombre());
            while (!salir) {
                System.out.println("Indica la acción a realizar:");
                System.out.println("1.-Vender un coche.");
                System.out.println("2.-Reservar un coche.");
                System.out.println("3.-Cancelar reserva de un coche.");
                System.out.println("4.-Consultar coches.");
                System.out.println("5.-Consultar exposiciones.");
                System.out.println("6.-Consultar clientes.");
                System.out.println("9.-Volver.");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        try {
                            venderCoche(vendedor);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            reservarCoche(vendedor);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                            cancelarReserva(vendedor);
                        } catch (ExceptionParametrosInvalidos e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case 4:
                        if (concesionario.getListadoCochesTotalesDefinitivo().isEmpty()) {
                            System.out.println("No hay coches en el concesionario.");
                        } else {
                            System.out.println(concesionario.verCochesConcesionario());
                        }
                        break;
                    case 5:
                        try {
                            verDatosExposicion();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        if (concesionario.getListadoClientes().isEmpty()) {
                            System.out.println("No hay clientes dados de alta.");
                        } else {
                            System.out.println(concesionario.verListaClientes());
                        }
                        break;
                    case 9:
                        menu();
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción incorrecta");
                }
            }
        }
    }

    private void menuMecanico() throws NullMecanicoException {
        if (concesionario.getListadoMecanicos().isEmpty()) {
            throw new NullMecanicoException();
        }
        System.out.println("Indica tu DNI para acceder:");
        String dni = sc.next();
        do {
            try {
                concesionario.existeMecanico(dni);
                repetir = false;
            } catch (NullMecanicoException e) {
                System.out.println(e.getMessage());
                System.out.println("Indica un DNI correcto o escribe 'salir' para volver.");
                dni = sc.next();
                if (!dni.equals("salir")) {
                    repetir = true;
                } else if (dni.equals("salir")) {
                    repetir = false;
                }
            }
        } while (repetir);
        Mecanico mecanico = concesionario.getListadoMecanicos().get(dni);
        if (mecanico != null) {
            System.out.println("Bienvenido " + mecanico.getNombre());
            while (!salir) {
                System.out.println("Elige que deseas hacer.");
                System.out.println("1.-Reparar un coche.");
                System.out.println("2.-Solucionar reparación.");
                System.out.println("3.-Consultar listado reparaciones realizadas.");
                System.out.println("9.-Volver.");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        try {
                            mandarRepararCoche();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            solucionarReparacion();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        if (concesionario.getListadoCochesTotalesDefinitivo().isEmpty()) {
                            System.out.println("No hay coche en el concesionario dados de alta.");
                        } else {
                            System.out.println("Indica la matrícula del coche a consultar:");
                            String matricula = sc.next();
                            try {
                                concesionario.existeCoche(matricula);
                                consultarReparaciones(matricula);
                            } catch (NullCocheException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                        break;
                    case 9:
                        menu();
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción incorrecta");
                }
            }
        }
    }
}
