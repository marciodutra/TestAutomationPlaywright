package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.base.BaseTest;
import br.com.sistemaescolar.pages.LoginPage;
import br.com.sistemaescolar.utils.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    public void deveRealizarLoginComSucesso() {

        LoginPage loginPage = new LoginPage(page);

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
}