package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.base.BaseTest;
import br.com.sistemaescolar.factories.AlunoFactory;
import br.com.sistemaescolar.factories.MatriculaFactory;
import br.com.sistemaescolar.factories.TurmaFactory;
import br.com.sistemaescolar.models.Aluno;
import br.com.sistemaescolar.models.Matricula;
import br.com.sistemaescolar.models.Turma;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MatriculasTest extends BaseTest {


    @Test
    public void deveCadastrarNovaMatricula(){


        realizarLogin();


        // Criar aluno

        dashboard.acessarMenu("Alunos");

        Aluno aluno =
                AlunoFactory.criar();


        String mensagemAluno =
                alunos.cadastrar(aluno);


        Assertions.assertEquals(
                "Aluno cadastrado com sucesso!",
                mensagemAluno
        );



        // Criar turma

        dashboard.acessarMenu("Turmas");


        Turma turma =
                TurmaFactory.criar();


        String mensagemTurma =
                turmas.cadastrar(turma);


        Assertions.assertEquals(
                "Turma criada",
                mensagemTurma
        );



        // Criar matrícula

        dashboard.acessarMenu("Matrículas");


        Matricula matricula =
                MatriculaFactory.criar(
                        aluno.getNome(),
                        turma.getNome()
                                + " - "
                                + turma.getAno()
                );


        String mensagem =
                matriculas.cadastrar(matricula);



        Assertions.assertEquals(
                "Matrícula realizada!",
                mensagem
        );


    }

    @Test
    public void deveAbrirFormularioNovaMatricula(){

        realizarLogin();

        dashboard.acessarMenu("Matrículas");

        matriculas.clicarNovaMatricula();

        Assertions.assertTrue(
                matriculas.formularioNovaMatriculaAberto(),
                "O formulário de matrícula não foi aberto."
        );

    }

    @Test
    public void deveEncontrarMatriculaCadastradaNaLista(){

        realizarLogin();


        dashboard.acessarMenu("Alunos");

        Aluno aluno =
                AlunoFactory.criar();

        alunos.cadastrar(aluno);



        dashboard.acessarMenu("Turmas");

        Turma turma =
                TurmaFactory.criar();

        turmas.cadastrar(turma);



        dashboard.acessarMenu("Matrículas");


        Matricula matricula =
                MatriculaFactory.criar(
                        aluno.getNome(),
                        turma.getNome()
                                + " - "
                                + turma.getAno()
                );


        matriculas.cadastrar(matricula);


        dashboard.acessarMenu("Matrículas");


        matriculas.pesquisarMatricula(
                aluno.getNome()
        );


        Assertions.assertTrue(
                matriculas.matriculaApareceNaLista(
                        aluno.getNome()
                ),
                "A matrícula não apareceu na lista."
        );

    }

    @Test
    public void naoDeveCadastrarMatriculaDuplicada(){

        realizarLogin();


        dashboard.acessarMenu("Alunos");

        Aluno aluno =
                AlunoFactory.criar();

        alunos.cadastrar(aluno);



        dashboard.acessarMenu("Turmas");

        Turma turma =
                TurmaFactory.criar();

        turmas.cadastrar(turma);



        dashboard.acessarMenu("Matrículas");


        Matricula matricula =
                MatriculaFactory.criar(
                        aluno.getNome(),
                        turma.getNome()
                                + " - "
                                + turma.getAno()
                );


        // Primeira matrícula
        matriculas.cadastrar(matricula);


        // Segunda tentativa com os mesmos dados
        String mensagem =
                matriculas.cadastrar(matricula);



        Assertions.assertEquals(
                "Erro ao matricular",
                mensagem
        );

    }

}