package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.utils.GeradorCpf;
import br.com.sistemaescolar.utils.ValidadorCpf;

public class TesteGeradorCpf {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            String cpf = GeradorCpf.gerar();

            System.out.println(
                    cpf + " - "
                            + ValidadorCpf.validar(cpf)
            );

        }

    }
}