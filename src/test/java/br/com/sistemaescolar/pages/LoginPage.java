package br.com.sistemaescolar.pages;

import br.com.sistemaescolar.utils.Config;
import com.microsoft.playwright.Page;

public class LoginPage {

    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void abrirSistema() {
        page.navigate(Config.get("url"));
    }

    public void preencherEmail(String email) {
        page.getByPlaceholder("Email")
                .fill(email);
    }

    public void preencherSenha(String senha) {
        page.getByPlaceholder("Senha")
                .fill(senha);
    }

    public void clicarEntrar() {

        var botao = page.locator("button",
                new Page.LocatorOptions().setHasText("Entrar"));

        System.out.println("Quantidade de botões encontrados: " + botao.count());

        botao.click();

        System.out.println("Clique executado");
    }

    public void fazerLogin(String email, String senha) {

        preencherEmail(email);
        preencherSenha(senha);

        clicarEntrar();
    }

    public boolean usuarioEstaLogado() {

        page.waitForURL("**/dashboard");

        page.locator("text=Bem-vindo, Márcio Dutra")
                .waitFor();

        return page
                .locator("text=Bem-vindo, Márcio Dutra")
                .isVisible();
    }

}