package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.base.BaseTest;
import br.com.sistemaescolar.pages.AlunosPage;
import br.com.sistemaescolar.pages.DashboardPage;
import br.com.sistemaescolar.pages.LoginPage;
import br.com.sistemaescolar.utils.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DashboardTest extends BaseTest {

    @Test
    public void deveAcessarDashboard() {

        realizarLogin();

        Assertions.assertTrue(
                dashboard.estaNoDashboard(),
                "O usuário não chegou ao Dashboard."
        );

    }

    @Test
    public void deveAbrirTelaDeAlunos() {

        realizarLogin();

        dashboard.acessarMenu("Alunos");

        page.waitForTimeout(3000);

    }

    @Test
    public void deveAbrirFormularioNovoAluno() {

        realizarLogin();

        dashboard.acessarMenu("Alunos");

        alunos.clicarNovoAluno();

        Assertions.assertTrue(
                alunos.formularioNovoAlunoAberto(),
                "O formulário de cadastro do aluno não foi aberto."
        );

    }

    @Test
    public void deveCadastrarAlunoSomenteComCpf() {

        realizarLogin();

        dashboard.acessarMenu("Alunos");

        alunos.clicarNovoAluno();

        alunos.preencherCpf("12345678901");

        alunos.clicarSalvar();

        page.waitForTimeout(5000);

    }

}