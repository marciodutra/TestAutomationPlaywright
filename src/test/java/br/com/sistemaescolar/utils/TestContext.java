package br.com.sistemaescolar.utils;

import com.microsoft.playwright.Page;

public class TestContext {

    private static String ultimoErro;

    private static String urlAtual;

    private static Page page;


    public static void salvarErro(String erro) {

        ultimoErro = erro;

    }


    public static String getUltimoErro() {

        return ultimoErro;

    }


    public static void salvarUrl(String url) {

        urlAtual = url;

    }


    public static String getUrlAtual() {

        return urlAtual;

    }


    public static void salvarPage(Page pageAtual) {

        page = pageAtual;

    }


    public static Page getPage() {

        return page;

    }


    public static void limpar() {

        ultimoErro = null;
        urlAtual = null;
        page = null;

    }

}