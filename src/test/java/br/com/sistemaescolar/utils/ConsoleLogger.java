package br.com.sistemaescolar.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleLogger {

    private static final List<String> mensagens =
            new ArrayList<>();

    public static void adicionar(String mensagem) {

        mensagens.add(mensagem);

    }

    public static List<String> obterMensagens() {

        return Collections.unmodifiableList(mensagens);

    }

    public static void limpar() {

        mensagens.clear();

    }

}