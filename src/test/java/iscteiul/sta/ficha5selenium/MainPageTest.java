package iscteiul.sta.ficha5selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By; // <-- Novo import necessário
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
        // Adicionamos Implicit Wait para ajudar a PageFactory a encontrar elementos não dinâmicos
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.jetbrains.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage = new MainPage(driver);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(mainPage.acceptCookiesButton)).click();
        } catch (TimeoutException e) {
            System.out.println("Aviso: Botão de cookies não apareceu. Prosseguindo.");
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

        // 2. 🚨 CORREÇÃO: Usamos visibilityOfElementLocated para garantir que o input é encontrado no DOM
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='search-input']"))
        );
        input.sendKeys("Selenium");
        input.sendKeys(Keys.ENTER);

        // 3. Validação: Espera que o título da nova página contenha o termo
        wait.until(ExpectedConditions.titleContains("Selenium"));
        assertTrue(driver.getTitle().contains("Selenium") || driver.getCurrentUrl().contains("q=Selenium"),
                "A pesquisa por 'Selenium' falhou.");
    }

    @Test
    public void toolsMenu() {
        // 1. Clicar no menu "Developer Tools"
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.developerToolsMenu)).click();

        // 2. 🚨 CORREÇÃO: Usamos visibilityOfElementLocated e o novo XPath para o link
        WebElement subLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[normalize-space(text())='All products' and contains(@href, '/all-products/')]")
                )
        );
        assertTrue(subLink.isDisplayed(), "O submenu de Developer Tools deveria estar visível.");
    }

    @Test
    public void navigationToAllTools() {
        // 1. Abrir o menu Developer Tools
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.developerToolsMenu)).click();

        // 2. 🚨 CORREÇÃO: Usamos elementToBeClickable e o novo XPath para o link
        WebElement allProductsLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[normalize-space(text())='All products' and contains(@href, '/all-products/')]")
                )
        );
        allProductsLink.click();

        // 3. Validação: Espera e verifica o título da nova página
        wait.until(ExpectedConditions.titleContains("All Developer Tools"));
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}