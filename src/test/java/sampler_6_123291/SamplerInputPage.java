package sampler_6_123291;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration; // Import necessário para o tempo de espera

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class SamplerInputPage {

    private static final String URL = "https://vaadin.com/docs/latest/components/text-field";

    // 1. Elementos (Tenta apanhar pelo texto "Accept" que vimos na imagem)
    private final SelenideElement cookieButton = $(byText("Accept"));
    // Alternativa pelo ID técnico caso o texto mude
    private final SelenideElement cookieButtonBackup = $("#onetrust-accept-btn-handler");

    // O componente principal
    private final SelenideElement textFieldHost = $("vaadin-text-field");

    // 2. Abrir página
    public void openSampler() {
        open(URL);
    }

    // 3. Método aceitar cookies (Melhorado para ESPERAR pelo botão)
    public void acceptCookies() {
        // Tenta esperar até 5 segundos pelo botão "Accept"
        try {
            if (cookieButton.shouldBe(visible, Duration.ofSeconds(5)).exists()) {
                cookieButton.click();
            }
        } catch (AssertionError e) {
            // Se o botão "Accept" não aparecer, tenta o ID de backup
            if (cookieButtonBackup.exists() && cookieButtonBackup.isDisplayed()) {
                cookieButtonBackup.click();
            }
        }
    }

    // 4. Interação com o input (Injeção direta JS)
    public void setInputText(String text) {
        textFieldHost.shouldBe(visible).scrollTo();
        executeJavaScript("arguments[0].value = arguments[1];", textFieldHost, text);
        executeJavaScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", textFieldHost);
    }

    public String getInputValue() {
        return executeJavaScript("return arguments[0].value;", textFieldHost);
    }
}
