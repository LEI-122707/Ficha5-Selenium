package sampler_6_123291;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SamplerInputTest {

    SamplerInputPage page = new SamplerInputPage();

    @BeforeAll
    public static void config() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void init() {
        page.openSampler();
        // Agora este método vai esperar que o banner apareça antes de clicar
        page.acceptCookies();
    }

    @Test
    @DisplayName("Teste Sampler 6: Data Input")
    public void testDataInput() {
        String textoTeste = "Teste Funcional";

        page.setInputText(textoTeste);

        String resultado = page.getInputValue();
        assertEquals(textoTeste, resultado);
    }
}
