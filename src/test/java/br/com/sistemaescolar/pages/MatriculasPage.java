package br.com.sistemaescolar.pages;

import br.com.sistemaescolar.base.BasePage;
import br.com.sistemaescolar.models.Matricula;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

public class MatriculasPage extends BasePage {


    private final Locator btnNovaMatricula;
    private final Locator cmbAluno;
    private final Locator cmbTurma;
    private final Locator btnMatricular;
    private final Locator txtPesquisar;


    public MatriculasPage(Page page) {

        super(page);


        btnNovaMatricula = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName("+ Nova matrícula")
        );


        cmbAluno = page.locator("select").nth(0);


        cmbTurma = page.locator("select").nth(1);


        btnMatricular = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName("Matricular")
        );


        txtPesquisar = page.getByPlaceholder(
                "Pesquisar aluno ou turma..."
        );

    }


    public void clicarNovaMatricula(){

        btnNovaMatricula.click();

    }


    public void selecionarAluno(String aluno){

        cmbAluno.selectOption(
                new SelectOption()
                        .setLabel(aluno)
        );

    }


    public void selecionarTurma(String turma){

        cmbTurma.waitFor();

        cmbTurma.selectOption(
                new SelectOption()
                        .setLabel(turma)
        );

    }


    public void preencherFormulario(Matricula matricula){

        selecionarAluno(
                matricula.getAluno()
        );


        selecionarTurma(
                matricula.getTurma()
        );

    }


    public void clicarMatricular(){

        btnMatricular.click();

    }


    public String cadastrar(Matricula matricula){

        clicarNovaMatricula();

        preencherFormulario(matricula);

        clicarMatricular();


        return capturarToast("Matrícula realizada");

    }

    public void listarOpcoesSelects(){

        int quantidade =
                page.locator("select").count();

        for(int i = 0; i < quantidade; i++){

            System.out.println("SELECT " + i);

            Locator select =
                    page.locator("select").nth(i);

            int opcoes =
                    select.locator("option").count();

            for(int j = 0; j < opcoes; j++){

                System.out.println(
                        select.locator("option")
                                .nth(j)
                                .innerText()
                );

            }

        }

    }


}