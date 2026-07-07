package br.com.sistemaescolar.utils;

public class ValidadorCpf {

    public static boolean validar(String cpf) {

        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int[] numeros = new int[11];

        for (int i = 0; i < 11; i++) {
            numeros[i] = Integer.parseInt(
                    cpf.substring(i, i + 1)
            );
        }


        int primeiroDigito = calcularPrimeiroDigito(numeros);

        int segundoDigito = calcularSegundoDigito(
                numeros,
                primeiroDigito
        );


        return numeros[9] == primeiroDigito
                &&
                numeros[10] == segundoDigito;

    }


    private static int calcularPrimeiroDigito(int[] numeros) {

        int soma = 0;
        int peso = 10;

        for (int i = 0; i < 9; i++) {

            soma += numeros[i] * peso;
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


        for (int i = 0; i < 9; i++) {

            soma += numeros[i] * peso;
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