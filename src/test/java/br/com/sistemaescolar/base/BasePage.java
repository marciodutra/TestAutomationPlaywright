package br.com.sistemaescolar.base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public abstract class BasePage {

    protected final Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    protected Locator locator(String selector) {
        return page.locator(selector);
    }

    protected void navegar(String url) {
        page.navigate(url);
    }

    protected void preencherPorPlaceholder(String placeholder, String valor) {
        page.getByPlaceholder(placeholder).fill(valor);
    }

    protected void clicarPorRole(String nome) {

        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(nome)
        ).click();

    }

    protected void selecionarOption(int indice, String valor) {

        page.locator("select")
                .nth(indice)
                .selectOption(valor);

    }

    protected boolean textoVisivel(String texto) {

        return page.locator("text=" + texto)
                .isVisible();

    }


}