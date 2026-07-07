package br.com.sistemaescolar.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AlunosPage {

    private final Page page;

    public AlunosPage(Page page) {
        this.page = page;
    }

    public void clicarNovoAluno() {

        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("+ Novo aluno")
        ).click();

    }

    public boolean formularioNovoAlunoAberto() {

        return page.locator("text=Cadastro de Aluno")
                .isVisible();

    }

    public void preencherCpf(String cpf) {

        page.getByPlaceholder("CPF")
                .fill(cpf);

    }

    public void clicarSalvar() {

        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Salvar aluno")
        ).click();

    }

}