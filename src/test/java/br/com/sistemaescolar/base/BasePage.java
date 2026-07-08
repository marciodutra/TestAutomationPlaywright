package br.com.sistemaescolar.base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

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
}