package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.utils.GeradorCpf;
import br.com.sistemaescolar.utils.ValidadorCpf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GeradorCpfTest {


    @Test
    public void deveGerarCpfValido() {

        String cpf = GeradorCpf.gerar();


        Assertions.assertEquals(
                11,
                cpf.length(),
                "CPF deve possuir 11 dígitos"
        );


        Assertions.assertTrue(
                ValidadorCpf.validar(cpf),
                "CPF gerado não é válido"
        );

    }


    @Test
    public void deveGerarCpfsDiferentes() {

        String cpf1 = GeradorCpf.gerar();

        String cpf2 = GeradorCpf.gerar();


        Assertions.assertNotEquals(
                cpf1,
                cpf2,
                "Os CPFs gerados não podem ser iguais"
        );

    }

}