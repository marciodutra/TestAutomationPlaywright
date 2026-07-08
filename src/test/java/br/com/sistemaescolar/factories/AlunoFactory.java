package br.com.sistemaescolar.factories;

import br.com.sistemaescolar.models.Aluno;
import br.com.sistemaescolar.utils.GeradorCpf;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlunoFactory {


    public static Aluno criar() {

        Aluno aluno = new Aluno();

        String identificador =
                new SimpleDateFormat("yyyyMMddHHmmss")
                        .format(new Date());


        aluno.setNome(
                "Aluno Teste QA " + identificador
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
                "aluno" + identificador + "@teste.com"
        );


        aluno.setEstado(
                "RS"
        );


        return aluno;

    }

}