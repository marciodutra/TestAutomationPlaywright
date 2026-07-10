package br.com.sistemaescolar.pages;

import br.com.sistemaescolar.base.BasePage;
import br.com.sistemaescolar.models.Turma;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

public class TurmasPage extends BasePage {

    private final Locator btnNovaTurma;
    private final Locator txtNome;
    private final Locator txtAno;
    private final Locator cmbProfessor;
    private final Locator btnSalvar;
    private final Locator txtPesquisar;

    public TurmasPage(Page page) {
        super(page);

        btnNovaTurma = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("+ Nova turma")
        );

        txtNome = page.getByPlaceholder("Nome");

        txtAno = page.getByPlaceholder("Ano");

        cmbProfessor = page.locator("select");

        btnSalvar = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Salvar")
        );

        txtPesquisar = page.getByPlaceholder("Pesquisar turma...");
    }

    public void clicarNovaTurma() {

        btnNovaTurma.click();

    }

    public void preencherNome(String nome) {

        txtNome.fill(nome);

    }

    public void preencherAno(String ano) {

        txtAno.fill(ano);

    }

    public void selecionarProfessor(String professor) {

        cmbProfessor.selectOption(
                new SelectOption().setLabel(professor)
        );

    }

    public void preencherFormulario(Turma turma) {

        preencherNome(turma.getNome());

        preencherAno(turma.getAno());

        selecionarProfessor(turma.getProfessor());

    }

    public void clicarSalvar() {

        btnSalvar.click();

    }

    public String cadastrar(Turma turma) {

        clicarNovaTurma();

        preencherFormulario(turma);

        clicarSalvar();

        Locator toast = page.locator(".Toastify__toast");

        toast.waitFor();

        return toast.innerText();

    }

    public boolean formularioNovaTurmaAberto() {

        return page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Nova Turma")
        ).isVisible();

    }

    public void pesquisarTurma(String nome) {

        txtPesquisar.fill(nome);

        page.waitForTimeout(1000);

    }

    public boolean turmaApareceNaLista(String nome) {

        return page.getByText(nome).isVisible();

    }

}