package br.com.sistemaescolar.factories;

import br.com.sistemaescolar.models.Turma;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TurmaFactory {

    public static Turma criar() {

        Turma turma = new Turma();

        String identificador =
                new SimpleDateFormat("HHmmss")
                        .format(new Date());

        turma.setNome(
                "Turma QA " + identificador
        );

        turma.setAno(
                "2026"
        );

        // Será selecionado no combo
        turma.setProfessor("Márcio prof");

        return turma;

    }

}