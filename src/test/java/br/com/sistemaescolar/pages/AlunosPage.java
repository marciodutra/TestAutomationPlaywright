package br.com.sistemaescolar.pages;

import br.com.sistemaescolar.models.Aluno;
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
                new Page.GetByRoleOptions()
                        .setName("+ Novo aluno")
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
                new Page.GetByRoleOptions()
                        .setName("Salvar aluno")
        ).click();

    }


    public void preencherFormulario(Aluno aluno) {

        preencherNome(
                aluno.getNome()
        );


        preencherCpf(
                aluno.getCpf()
        );


        selecionarSexo(
                aluno.getSexo()
        );


        preencherDataNascimento(
                aluno.getDataNascimento()
        );


        preencherSenha(
                aluno.getSenha()
        );

        selecionarEstado(
                aluno.getEstado()
        );


        preencherEmail(
                aluno.getEmail()
        );

    }


    public void cadastrar(Aluno aluno) {

        clicarNovoAluno();

        preencherFormulario(aluno);

        clicarSalvar();

    }

    public void preencherNome(String nome){

        page.getByPlaceholder("Nome")
                .fill(nome);

    }


    public void selecionarSexo(String sexo){

        page.locator("select")
                .nth(0)
                .selectOption(sexo);

    }


    public void preencherDataNascimento(String data){

        page.locator("input[type='date']")
                .fill(data);

    }


    public void preencherSenha(String senha){

        page.getByPlaceholder("Senha")
                .fill(senha);

    }


    public void preencherEmail(String email){

        page.getByPlaceholder("Email")
                .fill(email);

    }

    public void selecionarEstado(String estado){

        page.locator("select")
                .nth(1)
                .selectOption(estado);

    }

}