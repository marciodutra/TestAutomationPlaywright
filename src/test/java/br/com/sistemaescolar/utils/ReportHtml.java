package br.com.sistemaescolar.utils;

import br.com.sistemaescolar.models.ResultadoTeste;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

            Files.createDirectories(
                    arquivo.getParent()
            );


            int total =
                    resultados.size();


            long passou =
                    resultados.stream()
                            .filter(r -> r.getStatus().equals("PASSOU"))
                            .count();


            long falhou =
                    resultados.stream()
                            .filter(r -> r.getStatus().equals("FALHOU"))
                            .count();


            double percentual =
                    total == 0
                            ? 0
                            : (passou * 100.0) / total;


            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern(
                            "dd/MM/yyyy HH:mm:ss"
                    );


            StringBuilder html =
                    new StringBuilder();


            html.append("""
                <!DOCTYPE html>
                <html lang="pt-BR">

                <head>

                <meta charset="UTF-8">

                <title>
                    Sistema Escolar - Relatório de Testes
                </title>


                <style>

                body {

                    font-family: Arial, Helvetica, sans-serif;
                    background: #f4f6f8;
                    margin: 0;
                    padding: 30px;

                }


                .container {

                    max-width: 1200px;
                    margin: auto;

                }


                .header {

                    background: white;
                    padding: 25px;
                    border-radius: 12px;
                    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                    margin-bottom: 25px;

                }


                h1 {

                    margin:0;
                    color:#333;

                }


                .data {

                    color:#666;
                    margin-top:10px;

                }


                .cards {

                    display:flex;
                    gap:20px;
                    margin-bottom:25px;

                }


                .card {

                    flex:1;
                    background:white;
                    padding:20px;
                    border-radius:12px;
                    text-align:center;
                    box-shadow:0 4px 12px rgba(0,0,0,0.1);

                }


                .numero {

                    font-size:32px;
                    font-weight:bold;

                }


                .titulo {

                    color:#666;
                    margin-top:8px;

                }


                .verde {

                    color:#198754;

                }


                .vermelho {

                    color:#dc3545;

                }


                .azul {

                    color:#0d6efd;

                }


                table {

                    width:100%;
                    border-collapse:collapse;
                    background:white;
                    border-radius:12px;
                    overflow:hidden;
                    box-shadow:0 4px 12px rgba(0,0,0,0.1);

                }


                th {

                    background:#0d6efd;
                    color:white;
                    padding:15px;
                    text-align:left;

                }


                td {

                    padding:12px;
                    border-bottom:1px solid #ddd;

                }


                .status {

                    padding:6px 12px;
                    border-radius:20px;
                    font-weight:bold;
                    display:inline-block;

                }


                .passou {

                    background:#d1e7dd;
                    color:#0f5132;

                }


                .falhou {

                    background:#f8d7da;
                    color:#842029;

                }


                a {

                    text-decoration:none;
                    font-weight:bold;

                }


                .rodape {

                    margin-top:30px;
                    text-align:center;
                    color:#777;

                }


                </style>

                </head>


                <body>


                <div class="container">


                <div class="header">

                    <h1>
                        Sistema Escolar Automation
                    </h1>


                    <div class="data">

                        Relatório gerado em:
                """);


            html.append(
                    LocalDateTime.now()
                            .format(formato)
            );


            html.append("""
                    </div>

                </div>



                <div class="cards">


                    <div class="card">

                        <div class="numero azul">
                """);


            html.append(total);


            html.append("""
                        </div>

                        <div class="titulo">
                            Total de testes
                        </div>

                    </div>



                    <div class="card">

                        <div class="numero verde">
                """);


            html.append(passou);


            html.append("""
                        </div>

                        <div class="titulo">
                            Aprovados
                        </div>

                    </div>



                    <div class="card">

                        <div class="numero vermelho">
                """);


            html.append(falhou);


            html.append("""
                        </div>

                        <div class="titulo">
                            Reprovados
                        </div>

                    </div>



                    <div class="card">

                        <div class="numero azul">
                """);


            html.append(
                    String.format("%.2f%%", percentual)
            );


            html.append("""
                        </div>

                        <div class="titulo">
                            Taxa de sucesso
                        </div>

                    </div>


                </div>



                <table>


                <tr>

                    <th>Teste</th>
                    <th>Data/Hora</th>
                    <th>Status</th>
                    <th>Evidências</th>

                </tr>

                """);



            for (ResultadoTeste resultado : resultados) {


                String classe =
                        resultado.getStatus()
                                .equals("PASSOU")
                                ? "passou"
                                : "falhou";


                html.append("<tr>");


                html.append("<td>")
                        .append(resultado.getNomeTeste())
                        .append("</td>");



                html.append("<td>")
                        .append(
                                resultado.getDataHora()
                                        .format(formato)
                        )
                        .append("</td>");



                html.append("<td>");

                html.append("<span class='status ")
                        .append(classe)
                        .append("'>")
                        .append(resultado.getStatus())
                        .append("</span>");

                html.append("</td>");



                html.append("<td>");

                html.append(
                                "<a target='_blank' rel='noopener noreferrer' href='"
                        )
                        .append(resultado.getScreenshot())
                        .append("'>📷 Screenshot</a>");

                html.append("<br>");

                html.append(
                                "<a target='_blank' rel='noopener noreferrer' href='"
                        )
                        .append(resultado.getLog())
                        .append("'>📄 Log</a>");


                html.append("</td>");


                html.append("</tr>");

            }



            html.append("""
                
                </table>


                <div class="rodape">

                    Framework desenvolvido com:
                    <br>
                    Java 17 • Playwright • JUnit 5

                </div>


                </div>


                </body>

                </html>

                """);



            Files.writeString(
                    arquivo,
                    html.toString()
            );


            System.out.println(
                    "Relatório HTML gerado com sucesso"
            );


        } catch (Exception e) {


            System.out.println(
                    "Erro ao gerar relatório HTML: "
                            + e.getMessage()
            );

        }

    }

}

