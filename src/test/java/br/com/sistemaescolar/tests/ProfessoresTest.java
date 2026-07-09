package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProfessoresTest extends BaseTest {

    @Test
    public void deveAbrirFormularioNovoProfessor() {

        realizarLogin();

        dashboard.acessarMenu("Professores");

        professores.clicarNovoProfessor();

        Assertions.assertTrue(
                professores.isFormularioNovoProfessorVisivel(),
                "O formulário de cadastro do professor não foi aberto."
        );

    }

}