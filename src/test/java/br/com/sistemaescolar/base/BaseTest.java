package br.com.sistemaescolar.base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import br.com.sistemaescolar.pages.LoginPage;
import br.com.sistemaescolar.pages.ProfessoresPage;
import br.com.sistemaescolar.utils.Config;
import br.com.sistemaescolar.pages.AlunosPage;
import br.com.sistemaescolar.pages.DashboardPage;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected LoginPage loginPage;
    protected DashboardPage dashboard;
    protected AlunosPage alunos;
    protected ProfessoresPage professores;

    @BeforeEach
    public void iniciar() {

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

        page.onConsoleMessage(msg ->
                System.out.println("CONSOLE: " + msg.text())
        );
    }

    protected void realizarLogin() {

        LoginPage loginPage = new LoginPage(page);

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

            String dataHora = LocalDateTime.now()
                    .format(
                            DateTimeFormatter.ofPattern(
                                    "yyyy-MM-dd_HH-mm-ss"
                            )
                    );


            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(
                                    Paths.get(
                                            "screenshots/evidencia_"
                                                    + dataHora
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
}