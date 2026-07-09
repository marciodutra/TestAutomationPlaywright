package br.com.sistemaescolar.factories;

import br.com.sistemaescolar.models.Professor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ProfessorFactory {

    public static Professor criar() {

        Professor professor = new Professor();

        String identificador =
                new SimpleDateFormat("yyyyMMddHHmmssSSS")
                        .format(new Date());

        professor.setNome(
                "Professor QA " + identificador
        );

        professor.setEmail(
                "professor" + identificador + "@teste.com"
        );

        professor.setSenha(
                gerarSenha()
        );

        professor.setDisciplina(
                gerarDisciplina()
        );

        professor.setTelefone(
                gerarTelefone()
        );

        return professor;

    }


    private static String gerarSenha() {

        return "Senha"
                + new Random().nextInt(9000) + 1000;

    }


    private static String gerarDisciplina() {

        String[] disciplinas = {
                "Informática",
                "Matemática",
                "Português",
                "História",
                "Geografia",
                "Ciências"
        };

        Random random = new Random();

        return disciplinas[random.nextInt(disciplinas.length)];

    }


    private static String gerarTelefone() {

        Random random = new Random();

        return "5199"
                + (10000000 + random.nextInt(89999999));

    }

}