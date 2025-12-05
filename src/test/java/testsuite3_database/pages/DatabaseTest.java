package testsuite3_database.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

/**
 * Test Suite 3: Database - Informação sobre filme
 * Objetivo: Filtrar um filme e validar que a grelha exibe o resultado correto.
 */
public class DatabaseTest {

    private final String BASE_URL = "https://vaadin-database-example.demo.vaadin.com/";
    private DatabaseExamplePage databasePage;

    @BeforeAll
    static void setUpAllure() {
        // Configura o listener Allure para capturar steps e screenshots
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
        // Configura o browser a ser usado pelo Selenide
        Configuration.browser = "chrome";
    }

    @BeforeEach
    public void setUp() {
        // Configuração Selenide: Abre a página e maximiza a janela
        open(BASE_URL);
        Configuration.startMaximized = true;
        databasePage = new DatabaseExamplePage();
    }

    @Test
    @DisplayName("TS-3.1: Pesquisar por 'Matrix' e validar o título do primeiro filme")
    public void testSearchMovieInformation() {
        // Dados de teste
        String searchTerm = "Matrix";
        String expectedTitle = "The Matrix";

        // 1. Ação: Filtrar a lista pelo termo de pesquisa
        databasePage.filterField.shouldHave(text("")); // Garante que o campo de pesquisa está visível
        databasePage.filterByTitle(searchTerm);

        // 2. Verificação: Esperar que o primeiro elemento da grelha contenha o título esperado
        // O Selenide espera automaticamente pelo elemento
        databasePage.firstGridRow.shouldHave(text(expectedTitle));

        // 3. Asserção final: Validação explícita (opcional, mas bom para garantir)
        Assertions.assertEquals(expectedTitle, databasePage.getFirstMovieTitle(),
                "O título do filme na primeira linha não corresponde ao esperado.");
    }
}