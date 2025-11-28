package iscteiul.sta.ficha5selenium.basicinteractions_122692;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Conjunto de testes para as interações básicas em elementos Web,
 * utilizando a aplicação de demonstração "The Internet Heroku App".
 * (Parte 1.B da Ficha Laboratorial).
 */
public class BasicInteractionsTest {
    private WebDriver driver;
    private final String BASE_URL = "https://the-internet.herokuapp.com";

    /**
     * Configuração inicial: Inicializa o driver e a espera implícita.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // A espera implícita ajuda a estabilizar os testes
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    /**
     * Limpeza: Encerra o browser após cada teste.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // --- EXEMPLO 1: Form Authentication (Login) ---

    /**
     * Teste de Caso de Uso: Login com Credenciais Válidas.
     * Objetivo: Validar a navegação e a mensagem de sucesso após o login.
     * Passos:
     * 1. Navegar para a página de Login.
     * 2. Inserir 'tomsmith' no username.
     * 3. Inserir 'SuperSecretPassword!' na password.
     * 4. Clicar em Login.
     * 5. Validar que a URL e a mensagem de status indicam sucesso.
     */
    @Test
    @DisplayName("Teste de Login: Sucesso com credenciais válidas")
    public void testSuccessfulLogin() {
        // 1. Navegar para a página de Login
        driver.get(BASE_URL + "/login");
        AuthPage authPage = new AuthPage(driver);

        // 2-4. Executar a ação de login
        authPage.login("tomsmith", "SuperSecretPassword!");

        // 5. Validar o resultado
        // A URL deve mudar para /secure
        assertEquals(BASE_URL + "/secure", driver.getCurrentUrl(), "A URL final deve ser /secure após o login.");

        // A mensagem de status deve indicar sucesso
        assertTrue(authPage.statusMessage.isDisplayed(), "A mensagem de status deve estar visível.");
        assertTrue(authPage.statusMessage.getText().contains("You logged into a secure area!"), "Mensagem de sucesso incorreta.");
    }

    // --- EXEMPLO 2: Checkboxes ---

    /**
     * Teste de Caso de Uso: Interação com Checkboxes.
     * Objetivo: Validar o estado inicial das caixas de seleção e alternar o seu estado.
     * Passos:
     * 1. Navegar para a página de Checkboxes.
     * 2. Validar que a primeira caixa (índice 0) está desmarcada.
     * 3. Clicar na primeira caixa e validar que ficou marcada.
     * 4. Validar que a segunda caixa (índice 1) está marcada.
     * 5. Clicar na segunda caixa e validar que ficou desmarcada.
     */
    @Test
    @DisplayName("Teste de Checkboxes: Alternar estado e validar")
    public void testCheckboxInteraction() {
        // 1. Navegar para a página de Checkboxes
        driver.get(BASE_URL + "/checkboxes");
        CheckboxPage checkboxPage = new CheckboxPage(driver);

        // Validações iniciais (Asserções)
        assertFalse(checkboxPage.checkboxes.get(0).isSelected(), "Checkbox 1 deveria estar desmarcada inicialmente.");
        assertTrue(checkboxPage.checkboxes.get(1).isSelected(), "Checkbox 2 deveria estar marcada inicialmente.");

        // 2. Ação: Marcar a primeira caixa
        checkboxPage.clickCheckbox(0);
        // Asserção: Verifica se ficou marcada
        assertTrue(checkboxPage.checkboxes.get(0).isSelected(), "Checkbox 1 não foi marcada após o clique.");

        // 3. Ação: Desmarcar a segunda caixa
        checkboxPage.clickCheckbox(1);
        // Asserção: Verifica se ficou desmarcada
        assertFalse(checkboxPage.checkboxes.get(1).isSelected(), "Checkbox 2 não foi desmarcada após o clique.");
    }
}