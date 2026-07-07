package br.com.sistemaescolar.utils;

public class TestData {

    public static String get(String chave) {

        return Config.get(chave, "testdata.properties");

    }

}