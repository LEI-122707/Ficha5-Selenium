package iscteiul.sta.ficha5selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By; // <-- Adicionar este import
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.jetbrains.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage = new MainPage(driver);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(mainPage.acceptCookiesButton)).click();
        } catch (TimeoutException e) {
            System.out.println("Aviso: Botão de cookies não apareceu ou já foi aceite. Prosseguindo.");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void search() {
        // 1. Clicar na lupa
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.searchButton)).click();

        // 2. 🚨 CORREÇÃO CRÍTICA: Em vez de usar mainPage.searchInput, usamos By.cssSelector
        // diretamente aqui para localizar o input[type='search'] dinamicamente.
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='search']"))
        );
        input.sendKeys("Selenium");
        input.sendKeys(Keys.ENTER);

        // 3. Validação: Espera que o título mude
        wait.until(ExpectedConditions.titleContains("Selenium"));
        assertTrue(driver.getTitle().contains("Selenium") || driver.getCurrentUrl().contains("q=Selenium"),
                "A pesquisa por 'Selenium' falhou.");
    }

    @Test
    public void toolsMenu() {
        // Teste 2: Abertura do Menu

        // 1. Clicar no menu "Developer Tools"
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.developerToolsMenu)).click();

        // 2. Validação: Agora esperamos pela visibilidade do novo seletor (XPath)
        WebElement subLink = wait.until(ExpectedConditions.visibilityOf(mainPage.seeAllToolsButton));
        assertTrue(subLink.isDisplayed(), "O submenu de Developer Tools deveria estar visível.");
    }

    @Test
    public void navigationToAllTools() {
        // Teste 3: Navegação para a página de todos os produtos

        // 1. Abrir o menu Developer Tools
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.developerToolsMenu)).click();

        // 2. Clicar em "All products" (com o novo seletor, deve funcionar)
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.seeAllToolsButton)).click();

        // 3. Validação: Espera e verifica o título da nova página
        wait.until(ExpectedConditions.titleContains("All Developer Tools"));
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}