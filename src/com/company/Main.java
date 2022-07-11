package com.company;

public class Main {

    public static void main(String[] args) {
        Concesionario c1 = null;
        try {
            c1 = new Concesionario();
        } catch (ExceptionParametrosInvalidos e){
            System.out.println(e.getMessage());
        }
            c1.menu();
    }
}

