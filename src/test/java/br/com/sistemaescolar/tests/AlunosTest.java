package br.com.sistemaescolar.tests;

import br.com.sistemaescolar.base.BaseTest;
import br.com.sistemaescolar.factories.AlunoFactory;
import br.com.sistemaescolar.models.Aluno;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlunosTest extends BaseTest {

    @Test
    public void deveAbrirFormularioNovoAluno() {

        realizarLogin();

        dashboard.acessarMenu("Alunos");

        alunos.clicarNovoAluno();

        Assertions.assertTrue(
                alunos.formularioNovoAlunoAberto(),
                "O formulário de cadastro do aluno não foi aberto."
        );

    }


    @Test
    public void deveCadastrarAlunoComDadosValidos() {

        realizarLogin();

        dashboard.acessarMenu("Alunos");

        Aluno aluno = AlunoFactory.criar();

        String mensagem = alunos.cadastrar(aluno);

        Assertions.assertEquals(
                "Aluno cadastrado com sucesso!",
                mensagem
        );

    }

    @Test
    public void deveEncontrarAlunoCadastradoNaLista() {

        realizarLogin();

        dashboard.acessarMenu("Alunos");

        Aluno aluno = AlunoFactory.criar();

        alunos.cadastrar(aluno);

        dashboard.acessarMenu("Alunos");

        alunos.pesquisarAluno(aluno.getNome());

        Assertions.assertTrue(
                alunos.alunoApareceNaLista(aluno.getNome()),
                "Aluno cadastrado não apareceu na lista."
        );

    }

    @Test
    public void naoDeveCadastrarAlunoSemNome() {

        realizarLogin();

        dashboard.acessarMenu("Alunos");

        Aluno aluno = AlunoFactory.criar();

        aluno.setNome("");

        String mensagem = alunos.cadastrar(aluno);

        Assertions.assertEquals(
                "Preencha os campos obrigatórios",
                mensagem
        );

    }

//    @Test
//    public void naoDeveCadastrarAlunoSemCpf() {
//
//        realizarLogin();
//
//        dashboard.acessarMenu("Alunos");
//
//        Aluno aluno = AlunoFactory.criar();
//
//        aluno.setCpf("");
//
//        String mensagem = alunos.cadastrar(aluno);
//
//        Assertions.assertEquals(
//                "Preencha os campos obrigatórios",
//                mensagem
//        );
//
//    }

//    @Test
//    public void naoDeveCadastrarAlunoSemEmail() {
//
//        realizarLogin();
//
//        dashboard.acessarMenu("Alunos");
//
//        Aluno aluno = AlunoFactory.criar();
//
//        aluno.setEmail("");
//
//        String mensagem = alunos.cadastrar(aluno);
//
//        Assertions.assertEquals(
//                "Preencha os campos obrigatórios",
//                mensagem
//        );
//
//    }

    @Test
    public void naoDeveCadastrarAlunoSemSenha() {

        realizarLogin();

        dashboard.acessarMenu("Alunos");

        Aluno aluno = AlunoFactory.criar();

        aluno.setSenha("");

        String mensagem = alunos.cadastrar(aluno);

        Assertions.assertEquals(
                "Senha obrigatória para novo aluno",
                mensagem
        );

    }
}
