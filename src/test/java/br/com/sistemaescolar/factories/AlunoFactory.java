package br.com.sistemaescolar.factories;

import br.com.sistemaescolar.models.Aluno;
import br.com.sistemaescolar.utils.GeradorCpf;

public class AlunoFactory {


    public static Aluno criar() {

        Aluno aluno = new Aluno();


        aluno.setNome(
                "Aluno Teste"
        );


        aluno.setCpf(
                GeradorCpf.gerar()
        );


        aluno.setSexo(
                "Masculino"
        );


        aluno.setDataNascimento(
                "2010-01-01"
        );


        aluno.setSenha(
                "123456"
        );


        aluno.setEmail(
                "aluno" + System.currentTimeMillis() + "@teste.com"
        );

        aluno.setEstado(
                "RS"
        );


        return aluno;

    }

}