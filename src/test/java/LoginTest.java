import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class LoginTest {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setSlowMo(500)
            );

            Page page = browser.newPage();

            page.navigate("https://sistemaescolar-virid.vercel.app/");

            page.getByPlaceholder("Email")
                    .fill("professormarciodutra@gmail.com");

            page.getByPlaceholder("Senha")
                    .fill("Md@051080");

            page.getByRole(
                            AriaRole.BUTTON,
                            new Page.GetByRoleOptions().setName("Entrar"))
                    .click();

            // Aguarda a navegação
            page.waitForURL("**/dashboard");

            // Validação
            if (page.url().contains("/dashboard")) {
                System.out.println("✅ Login realizado com sucesso!");
            } else {
                System.out.println("❌ Falha no login!");
            }

            page.waitForTimeout(3000);

            browser.close();
        }
    }
}