package com.company;

import java.util.Scanner;

public class Concesionario {

    public Concesionario() {
    }

    public void menu() {
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

    public void gestionVendedoresComision() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;
    }

    public void gestionDirectorComercial(){

    }

    public void gestionMecanico(){

    }
}
