package br.com.sistemaescolar.pages;

import br.com.sistemaescolar.base.BasePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class ProfessoresPage extends BasePage {

    private final Locator btnNovoProfessor;

    public ProfessoresPage(Page page) {
        super(page);

        btnNovoProfessor = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("+ Novo professor")
        );
    }

    public void clicarNovoProfessor() {
        btnNovoProfessor.click();
    }

    public boolean formularioNovoProfessorAberto() {

        return page.locator("text=Cadastro de Professor")
                .isVisible();

    }

}