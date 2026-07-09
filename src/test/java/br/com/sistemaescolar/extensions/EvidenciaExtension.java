package br.com.sistemaescolar.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import br.com.sistemaescolar.utils.ConsoleLogger;
import br.com.sistemaescolar.models.ResultadoTeste;
import br.com.sistemaescolar.utils.ReportHtml;

public class EvidenciaExtension implements TestWatcher {


    private String nomeTeste(ExtensionContext context) {

        return context.getTestClass().get().getSimpleName()
                + "_"
                + context.getTestMethod().get().getName();

    }

    private void adicionarResultado(
            String nomeTeste,
            String status
    ) {

        ResultadoTeste resultado = new ResultadoTeste(
                nomeTeste,
                status,
                nomeTeste + ".png",
                nomeTeste + ".log",
                LocalDateTime.now()
        );

        ReportHtml.adicionar(resultado);

    }


    private void salvarLog(
            ExtensionContext context,
            String status,
            String erro
    ) {

        try {

            String nome =
                    nomeTeste(context);


            Path pasta =
                    Paths.get("evidencias");


            Files.createDirectories(pasta);


            Path arquivo =
                    pasta.resolve(nome + ".log");


            StringBuilder conteudo = new StringBuilder();

            conteudo.append("Teste: ")
                    .append(nome)
                    .append("\n");

            conteudo.append("Status: ")
                    .append(status)
                    .append("\n");

            conteudo.append("Data/Hora: ")
                    .append(LocalDateTime.now())
                    .append("\n\n");

            conteudo.append("Console:\n");

            for (String mensagem : ConsoleLogger.obterMensagens()) {

                conteudo.append(mensagem)
                        .append("\n");

            }

            if (erro != null) {

                conteudo.append("\nErro:\n")
                        .append(erro)
                        .append("\n");

            }


            Files.writeString(
                    arquivo,
                    conteudo.toString()
            );

            adicionarResultado(nome, status);

            ReportHtml.gerar();

            ConsoleLogger.limpar();



        } catch (Exception e) {

            System.out.println(
                    "Erro ao gerar log: "
                            + e.getMessage()
            );

        }

    }


    @Override
    public void testSuccessful(
            ExtensionContext context
    ) {

        salvarLog(
                context,
                "PASSOU",
                null
        );

    }


    @Override
    public void testFailed(
            ExtensionContext context,
            Throwable cause
    ) {

        salvarLog(
                context,
                "FALHOU",
                cause.getMessage()
        );

    }

}