package iscteiul.sta.ficha5selenium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {

    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void config() {
        // Mudei ligeiramente a resolução para diferenciar
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void start() {
        open("https://www.jetbrains.com/");
        // Lógica de cookie movida para a Page Class
        mainPage.acceptCookies();
    }

    @Test
    @DisplayName("Verificar funcionamento da busca")
    public void verifySearchFunction() {
        mainPage.openSearch();
        mainPage.typeInSearch("Selenium");

        // Validação usando sintaxe fluida
        mainPage.getSearchInput().shouldHave(value("Selenium"));

        // Clicar no botão de confirmar busca
        $("button[data-test='full-search-button']").click();
    }

    @Test
    @DisplayName("Validar abertura do menu de ferramentas")
    public void validateToolsMenu() {
        mainPage.toggleMenu();
        // Verificação direta
        $("[data-test-marker='Developer Tools']").should(appear);
    }

    @Test
    @DisplayName("Navegar para lista de produtos")
    public void checkToolsNavigation() {
        mainPage.openDevTools();
        mainPage.selectFindYourTools();

        $("#products-page").shouldBe(visible);

        String pageTitle = Selenide.title();
        assertEquals("All Developer Tools and Products by JetBrains", pageTitle);
    }
}