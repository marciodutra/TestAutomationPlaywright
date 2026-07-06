package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.utils.Config;

public class TesteConfig {

    public static void main(String[] args) {

        System.out.println(Config.get("url"));
        System.out.println(Config.get("email"));
        System.out.println(Config.get("senha"));

    }
}