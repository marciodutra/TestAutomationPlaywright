package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.base.BaseTest;
import br.com.sistemaescolar.factories.TurmaFactory;
import br.com.sistemaescolar.models.Turma;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TurmasTest extends BaseTest {

    @Test
    public void deveAbrirFormularioNovaTurma() {

        realizarLogin();

        dashboard.acessarMenu("Turmas");

        turmas.clicarNovaTurma();

        Assertions.assertTrue(
                turmas.formularioNovaTurmaAberto(),
                "O formulário de cadastro da turma não foi aberto."
        );

    }

    @Test
    public void deveCadastrarNovaTurma() {

        realizarLogin();

        dashboard.acessarMenu("Turmas");

        Turma turma = TurmaFactory.criar();

        String mensagem = turmas.cadastrar(turma);

        Assertions.assertEquals(
                "Turma criada",
                mensagem,
                "A turma não foi cadastrada com os dados informados."
        );

    }

    @Test
    public void deveEncontrarTurmaCadastradaNaLista() {

        realizarLogin();

        dashboard.acessarMenu("Turmas");

        Turma turma = TurmaFactory.criar();

        turmas.cadastrar(turma);

        dashboard.acessarMenu("Turmas");

        turmas.pesquisarTurma(turma.getNome());

        Assertions.assertTrue(
                turmas.turmaApareceNaLista(turma.getNome()),
                "A turma cadastrada não apareceu na lista."
        );

    }

}