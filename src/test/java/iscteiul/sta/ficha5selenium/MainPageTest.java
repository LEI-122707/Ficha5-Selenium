package iscteiul.sta.ficha5selenium;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();

        // usar tamanho fixo para forçar layout "desktop"
        driver.manage().window().setSize(new Dimension(1280, 900));

        // espera implícita (estratégia de espera do Selenium)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.jetbrains.com/");

        // aceitar cookies automaticamente (se aparecerem)
        try {
            Thread.sleep(1000); // dar tempo para o banner aparecer

            WebElement cookiesBanner = driver.findElement(By.cssSelector("div.ch2-container"));
            // em muitos sites o primeiro botão é "Accept" / "Aceitar"
            WebElement acceptButton = cookiesBanner.findElement(By.tagName("button"));
            acceptButton.click();
        } catch (NoSuchElementException e) {
            // não há banner -> segue
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        //driver.quit();
    }


    @Test
    public void search() throws InterruptedException {
        // abrir a caixa de pesquisa
        mainPage.searchButton.click();

        // esperar um bocadinho pela animação
        Thread.sleep(500);

        // campo de pesquisa na home (DOM atual usa data-test-id)
        WebElement searchField = driver.findElement(
                By.cssSelector("input[data-test-id='search-input']")
        );

        searchField.sendKeys("Selenium");

        // validar diretamente no mesmo campo (sem ir para a página de resultados)
        assertEquals("Selenium", searchField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() throws InterruptedException {
        // clicar no item de menu (Developer Tools ou similar) definido na MainPage
        mainPage.toolsMenu.click();

        // dar tempo para o submenu abrir
        Thread.sleep(500);

        // procurar submenus possíveis: desktop e mobile
        List<WebElement> submenus = driver.findElements(
                By.cssSelector("div[data-test='main-submenu'], div[data-test='mobile-main-submenu']")
        );

        // verificar se pelo menos UM submenu está visível
        boolean algumVisivel = false;
        for (WebElement submenu : submenus) {
            if (submenu.isDisplayed()) {
                algumVisivel = true;
                break;
            }
        }

        assertTrue(algumVisivel);
    }


    @Test
    public void navigationToAllTools() throws InterruptedException {
        // 1. Clicar no botão "See Developer Tools" (já vem da MainPage)
        mainPage.seeDeveloperToolsButton.click();
        Thread.sleep(1000);   // dar tempo para a navegação / menu

        // 2. Clicar no link que realmente está clicável: data-test="suggestion-link"
        WebElement findYourToolLink = driver.findElement(
                By.cssSelector("a[data-test='suggestion-link']")
        );
        findYourToolLink.click();
        Thread.sleep(1500);   // tempo para a página final carregar

        // 3. Verificar que estamos na página de produtos
        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }



}