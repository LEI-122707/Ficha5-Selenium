package iscteiul.sta.ficha5selenium.basicinteractions_123291; // ⚠️ Substitui TEUNUMERO pelo teu nº

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;

public class DropdownTest {

    DropdownPage page = new DropdownPage();

    @BeforeAll
    public static void setupAll() {
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setup() {
        page.openPage();
    }

    @Test
    @DisplayName("Testar seleção de opções no Dropdown")
    public void testDropdownSelection() {
        // 1. Selecionar "Option 1"
        page.selectByText("Option 1");

        // Validação: Verifica se a opção selecionada contém o texto "Option 1"
        page.getSelectedOption().shouldHave(text("Option 1"));

        // 2. Mudar para "Option 2"
        page.selectByText("Option 2");

        // Validação
        page.getSelectedOption().shouldHave(text("Option 2"));
    }
}