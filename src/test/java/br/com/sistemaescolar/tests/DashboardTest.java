package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.base.BaseTest;
import br.com.sistemaescolar.factories.AlunoFactory;
import br.com.sistemaescolar.models.Aluno;
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

}