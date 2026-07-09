package br.com.sistemaescolar.utils;

import br.com.sistemaescolar.models.ResultadoTeste;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReportHtml {

    private static final List<ResultadoTeste> resultados =
            new ArrayList<>();

    public static void adicionar(ResultadoTeste resultado) {

        resultados.add(resultado);

    }

    public static List<ResultadoTeste> obterResultados() {

        return Collections.unmodifiableList(resultados);

    }

    public static void limpar() {

        resultados.clear();

    }

    public static void gerar() {

        try {

            Path arquivo =
                    Paths.get("evidencias/index.html");

            StringBuilder html = new StringBuilder();

            html.append("""
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <title>Relatório de Testes</title>

                    <style>

                        body{
                            font-family: Arial;
                            margin:40px;
                            background:#f5f5f5;
                        }

                        h1{
                            color:#333;
                        }

                        table{
                            width:100%;
                            border-collapse:collapse;
                            background:white;
                        }

                        th,td{
                            border:1px solid #ddd;
                            padding:10px;
                            text-align:left;
                        }

                        th{
                            background:#1976d2;
                            color:white;
                        }

                        .passou{
                            color:green;
                            font-weight:bold;
                        }

                        .falhou{
                            color:red;
                            font-weight:bold;
                        }

                    </style>

                </head>

                <body>

                <h1>Relatório de Execução</h1>

                <table>

                <tr>

                    <th>Teste</th>
                    <th>Status</th>
                    <th>Screenshot</th>
                    <th>Log</th>

                </tr>

                """);
            for (ResultadoTeste resultado : resultados) {

                html.append("<tr>");

                html.append("<td>")
                        .append(resultado.getNomeTeste())
                        .append("</td>");

                html.append("<td class='")
                        .append(resultado.getStatus().equals("PASSOU")
                                ? "passou"
                                : "falhou")
                        .append("'>")
                        .append(resultado.getStatus())
                        .append("</td>");

                html.append("<td>");

                html.append("<a href='")
                        .append(resultado.getScreenshot())
                        .append("'>Abrir</a>");

                html.append("</td>");

                html.append("<td>");

                html.append("<a href='")
                        .append(resultado.getLog())
                        .append("'>Abrir</a>");

                html.append("</td>");

                html.append("</tr>");

            }

            html.append("""

                </table>

                </body>

                </html>

                """);

            Files.writeString(
                    arquivo,
                    html.toString()
            );

        } catch (Exception e) {

            System.out.println(
                    "Erro ao gerar relatório HTML: "
                            + e.getMessage()
            );

        }

    }



}

