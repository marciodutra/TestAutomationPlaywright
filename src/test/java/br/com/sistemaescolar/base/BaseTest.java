package br.com.sistemaescolar.base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Paths;
import br.com.sistemaescolar.pages.LoginPage;
import br.com.sistemaescolar.pages.ProfessoresPage;
import br.com.sistemaescolar.utils.Config;
import br.com.sistemaescolar.pages.AlunosPage;
import br.com.sistemaescolar.pages.DashboardPage;
import org.junit.jupiter.api.TestInfo;
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.LocalDateTime;
import br.com.sistemaescolar.extensions.EvidenciaExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import br.com.sistemaescolar.utils.ConsoleLogger;

@ExtendWith(EvidenciaExtension.class)

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected LoginPage loginPage;
    protected DashboardPage dashboard;
    protected AlunosPage alunos;
    protected ProfessoresPage professores;
    protected String nomeTeste;

    @BeforeEach
    public void iniciar(TestInfo testInfo) {

        nomeTeste =
                testInfo.getTestClass().get().getSimpleName()
                        + "_"
                        + testInfo.getTestMethod().get().getName();

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(500)
        );

        page = browser.newPage();


        loginPage = new LoginPage(page);
        dashboard = new DashboardPage(page);
        alunos = new AlunosPage(page);
        professores = new ProfessoresPage(page);

        page.onConsoleMessage(msg -> {

            String mensagem =
                    "[" + msg.type() + "] " + msg.text();

            ConsoleLogger.adicionar(mensagem);

            System.out.println(mensagem);

        });

    }

    protected void realizarLogin() {


        loginPage.abrirSistema();

        loginPage.fazerLogin(
                Config.get("email"),
                Config.get("senha")
        );

        loginPage.usuarioEstaLogado();

    }


    @AfterEach
    public void finalizar() {

        capturarEvidencia();

        if (page != null) {
            page.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }

    }

    private void capturarEvidencia() {

        try {

            if (page == null) {
                return;
            }


            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(
                                    Paths.get(
                                            "evidencias/"
                                                    + nomeTeste
                                                    + ".png"
                                    )
                            )
                            .setFullPage(true)
            );


            System.out.println(
                    "Evidência salva com sucesso"
            );


        } catch (Exception e) {

            System.out.println(
                    "Erro ao salvar evidência: "
                            + e.getMessage()
            );

        }
    }

    private void gerarLog() {

        try {

            Path pasta =
                    Paths.get("evidencias");

            Files.createDirectories(pasta);


            Path arquivo =
                    pasta.resolve(nomeTeste + ".log");


            String conteudo =
                    "Teste executado: " + nomeTeste + "\n"
                            + "Data/Hora: " + LocalDateTime.now() + "\n"
                            + "URL final: " + page.url() + "\n"
                            + "Status: EXECUTADO";


            Files.writeString(
                    arquivo,
                    conteudo
            );


            System.out.println(
                    "Log salvo com sucesso"
            );


        } catch (Exception e) {

            System.out.println(
                    "Erro ao salvar log: "
                            + e.getMessage()
            );

        }

    }
}