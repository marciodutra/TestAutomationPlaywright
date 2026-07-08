package br.com.sistemaescolar.pages;

import br.com.sistemaescolar.base.BasePage;
import br.com.sistemaescolar.models.Aluno;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class AlunosPage extends BasePage {

    private final Locator btnNovoAluno;
    private final Locator txtNome;
    private final Locator txtCpf;
    private final Locator txtSenha;
    private final Locator txtEmail;
    private final Locator dtNascimento;
    private final Locator cmbSexo;
    private final Locator cmbEstado;
    private final Locator btnSalvar;
    private final Locator txtPesquisa;

    public AlunosPage(Page page) {
        super(page);

        btnNovoAluno = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("+ Novo aluno")
        );

        txtNome = page.getByPlaceholder("Nome");

        txtCpf = page.getByPlaceholder("CPF");

        txtSenha = page.getByPlaceholder("Senha");

        txtEmail = page.getByPlaceholder("Email");

        txtPesquisa = page.getByPlaceholder("Pesquisar aluno...");

        dtNascimento = page.locator("input[type='date']");

        cmbSexo = page.locator("select").nth(0);

        cmbEstado = page.locator("select").nth(1);

        btnSalvar = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Salvar aluno")
        );
    }

    public void clicarNovoAluno() {
        btnNovoAluno.click();
    }

    public boolean formularioNovoAlunoAberto() {
        return page.locator("text=Cadastro de Aluno").isVisible();
    }

    public void preencherCpf(String cpf) {
        txtCpf.fill(cpf);
    }

    public void clicarSalvar() {
        btnSalvar.click();
    }

    public void preencherFormulario(Aluno aluno) {

        preencherNome(aluno.getNome());

        preencherCpf(aluno.getCpf());

        selecionarSexo(aluno.getSexo());

        preencherDataNascimento(aluno.getDataNascimento());

        preencherSenha(aluno.getSenha());

        selecionarEstado(aluno.getEstado());

        preencherEmail(aluno.getEmail());

    }

    public String cadastrar(Aluno aluno) {

        clicarNovoAluno();

        preencherFormulario(aluno);

        clicarSalvar();

        Locator toast = page.locator(".Toastify__toast");

        toast.waitFor();

        return toast.innerText();

    }

    public void preencherNome(String nome) {
        txtNome.fill(nome);
    }

    public void selecionarSexo(String sexo) {

        cmbSexo.selectOption(sexo);

    }

    public void preencherDataNascimento(String data) {

        dtNascimento.fill(data);

    }

    public void preencherSenha(String senha) {
        txtSenha.fill(senha);
    }

    public void preencherEmail(String email) {
        txtEmail.fill(email);
    }

    public void selecionarEstado(String estado) {

        cmbEstado.selectOption(estado);

    }

    public void pesquisarAluno(String nome) {

        txtPesquisa.fill(nome);

        page.waitForTimeout(1000);

    }

    public boolean alunoApareceNaLista(String nome) {

        return page.getByText(nome).isVisible();

    }

    public String tentarCadastrar(Aluno aluno) {

        clicarNovoAluno();

        preencherFormulario(aluno);

        clicarSalvar();

        Locator toast = page.locator(".Toastify__toast");

        toast.waitFor();

        return toast.innerText();

    }

}