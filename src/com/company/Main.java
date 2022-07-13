package com.company;

public class Main {

    public static void main(String[] args) {
        Menu menu = null;
        try {
            menu = new Menu();
        } catch (ExceptionParametrosInvalidos e){
            System.out.println(e.getMessage());
        }
            menu.menu();
    }
}

