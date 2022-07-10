package com.company;

public class Main {

    public static void main(String[] args) {
        try {
            Concesionario c1 = new Concesionario();
            c1.menu();
        } catch (ExceptionParametrosInvalidos e) {
            System.out.println(e.getMessage());
        }
    }
}

