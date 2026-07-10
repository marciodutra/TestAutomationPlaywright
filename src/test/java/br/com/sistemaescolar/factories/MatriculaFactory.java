package br.com.sistemaescolar.factories;

import br.com.sistemaescolar.models.Matricula;

public class MatriculaFactory {

    public static Matricula criar(
            String aluno,
            String turma
    ) {

        Matricula matricula = new Matricula();

        matricula.setAluno(aluno);

        matricula.setTurma(turma);

        return matricula;

    }

}