package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.base.BaseTest;
import br.com.sistemaescolar.pages.LoginPage;
import br.com.sistemaescolar.utils.Config;
import br.com.sistemaescolar.utils.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    public void deveRealizarLoginComSucesso() {

        loginPage.abrirSistema();

        loginPage.fazerLogin(
                Config.get("email"),
                Config.get("senha")
        );

        Assertions.assertTrue(
                loginPage.usuarioEstaLogado(),
                "Usuário não foi direcionado ao dashboard após login"
        );

    }

    @Test
    public void naoDevePermitirLoginComSenhaInvalida() {

        loginPage.abrirSistema();

        loginPage.fazerLogin(
                Config.get("email"),
                TestData.get("senhaInvalida")
        );

        Assertions.assertTrue(
                loginPage.estaNaTelaLogin(),
                "Usuário foi redirecionado mesmo com senha inválida"
        );

    }
}

