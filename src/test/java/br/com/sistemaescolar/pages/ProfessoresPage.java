package br.com.sistemaescolar.pages;

import br.com.sistemaescolar.base.BasePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class ProfessoresPage extends BasePage {

    private final Locator btnNovoProfessor;
    private final Locator txtNome;
    private final Locator txtEmail;
    private final Locator txtSenha;
    private final Locator txtDisciplina;
    private final Locator txtTelefone;
    private final Locator btnSalvar;
    private final Locator mensagemSucesso;
    private final Locator textoProfessor;

    public ProfessoresPage(Page page) {
        super(page);

        btnNovoProfessor = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("+ Novo professor")
        );
        txtNome = page.getByPlaceholder("Nome");

        txtEmail = page.getByPlaceholder("Email");

        txtSenha = page.getByPlaceholder("Senha");

        txtDisciplina = page.getByPlaceholder("Disciplina");

        txtTelefone = page.getByPlaceholder("Telefone");



        btnSalvar = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Salvar professor")
        );

        mensagemSucesso = page.getByText("Professor cadastrado com sucesso!");

        textoProfessor = page.getByText("Professor");
    }

    public void clicarNovoProfessor() {
        btnNovoProfessor.click();
    }

    public boolean isFormularioNovoProfessorVisivel() {
        return page.getByText("Cadastro de Professor").isVisible();
    }

    public void preencherNome(String nome) {

        txtNome.fill(nome);

    }

    public void preencherEmail(String email) {

        txtEmail.fill(email);

    }

    public void preencherSenha(String senha) {

        txtSenha.fill(senha);

    }

    public void preencherDisciplina(String disciplina) {

        txtDisciplina.fill(disciplina);

    }

    public void preencherTelefone(String telefone) {

        txtTelefone.fill(telefone);

    }

    public void clicarSalvar() {
        btnSalvar.click();
    }

    public void preencherFormularioProfessor(
            String nome,
            String email,
            String senha,
            String disciplina,
            String telefone
    ) {

        preencherNome(nome);
        preencherEmail(email);
        preencherSenha(senha);
        preencherDisciplina(disciplina);
        preencherTelefone(telefone);

    }

    public boolean isCadastroProfessorRealizadoComSucesso() {

        return mensagemSucesso.isVisible();

    }

    public boolean professorApareceNaLista(String nomeProfessor) {

        Locator professor =
                page.getByText(nomeProfessor);

        professor.waitFor();

        return professor.isVisible();

    }

}