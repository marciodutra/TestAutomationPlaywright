package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import br.com.sistemaescolar.factories.ProfessorFactory;
import br.com.sistemaescolar.models.Professor;

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

    @Test
    public void deveCadastrarNovoProfessor() {

        realizarLogin();

        dashboard.acessarMenu("Professores");

        professores.clicarNovoProfessor();

        Professor professor = ProfessorFactory.criar();

        professores.preencherFormularioProfessor(
                professor.getNome(),
                professor.getEmail(),
                professor.getSenha(),
                professor.getDisciplina(),
                professor.getTelefone()
        );

        professores.clicarSalvar();

        Assertions.assertTrue(
                professores.professorApareceNaLista(professor.getNome()),
                "O professor não apareceu na lista após o cadastro."
        );

    }

}