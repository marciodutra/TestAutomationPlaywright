package br.com.sistemaescolar.factories;

import br.com.sistemaescolar.models.Professor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfessorFactory {

    public static Professor criar() {

        Professor professor = new Professor();

        String identificador =
                new SimpleDateFormat("yyyyMMddHHmmss")
                        .format(new Date());

        professor.setNome(
                "Professor QA " + identificador
        );

        professor.setEmail(
                "professor" + identificador + "@teste.com"
        );

        professor.setSenha(
                "123456"
        );

        professor.setDisciplina(
                "Informática"
        );

        professor.setTelefone(
                "51999999999"
        );

        return professor;

    }

}