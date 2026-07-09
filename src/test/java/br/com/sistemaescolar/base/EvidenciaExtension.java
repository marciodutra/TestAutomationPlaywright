package br.com.sistemaescolar.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class EvidenciaExtension implements TestWatcher {


    private String nomeTeste(ExtensionContext context) {

        return context.getTestClass().get().getSimpleName()
                + "_"
                + context.getTestMethod().get().getName();

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


            String conteudo =
                    "Teste: " + nome + "\n"
                            + "Status: " + status + "\n"
                            + "Data/Hora: " + LocalDateTime.now()
                            + "\n";


            if (erro != null) {

                conteudo +=
                        "Erro: "
                                + erro
                                + "\n";

            }


            Files.writeString(
                    arquivo,
                    conteudo
            );


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