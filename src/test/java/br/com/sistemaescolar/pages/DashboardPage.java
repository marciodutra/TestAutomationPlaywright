package br.com.sistemaescolar.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage {

    private final Page page;

    public DashboardPage(Page page) {
        this.page = page;
    }

    public boolean estaNoDashboard() {

        page.waitForURL("**/dashboard");

        return page.url().contains("/dashboard");
    }

    public void acessarMenu(String menu) {

        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(menu)
        ).click();

    }

}