package br.com.sistemaescolar.utils;

import java.util.Random;

public class GeradorCpf {

    private static final Random RANDOM = new Random();

    public static String gerar() {

        int[] numeros = new int[9];

        StringBuilder cpf = new StringBuilder();

        for (int i = 0; i < numeros.length; i++) {

            numeros[i] = RANDOM.nextInt(10);

            cpf.append(numeros[i]);

        }

        int primeiroDigito = calcularPrimeiroDigito(numeros);

        int segundoDigito = calcularSegundoDigito(
                numeros,
                primeiroDigito
        );

        cpf.append(primeiroDigito);
        cpf.append(segundoDigito);

        return cpf.toString();

    }

    private static int calcularPrimeiroDigito(int[] numeros) {

        int soma = 0;
        int peso = 10;

        for (int numero : numeros) {
            soma += numero * peso;
            peso--;
        }

        int resto = soma % 11;

        if (resto < 2) {
            return 0;
        }

        return 11 - resto;
    }

    private static int calcularSegundoDigito(int[] numeros, int primeiroDigito) {

        int soma = 0;
        int peso = 11;

        for (int numero : numeros) {
            soma += numero * peso;
            peso--;
        }

        soma += primeiroDigito * 2;

        int resto = soma % 11;

        if (resto < 2) {
            return 0;
        }

        return 11 - resto;
    }
}