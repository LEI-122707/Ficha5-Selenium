package iscteiul.sta.ficha5selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    // Cookies e Lupa já estavam a funcionar
    @FindBy(xpath = "//button[contains(text(), 'Accept All')]")
    public WebElement acceptCookiesButton;

    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    // 🚨 CORREÇÃO 1: O seletor [data-test='search-input'] falhou. Usamos o input[type='search']
    // que aparece no cabeçalho após o clique na lupa.
    @FindBy(css = "input[type='search']")
    public WebElement searchInput;

    // 🚨 CORREÇÃO 2: O XPath "//button[contains(., 'Developer Tools')]" estava correto
    @FindBy(xpath = "//button[contains(., 'Developer Tools')]")
    public WebElement developerToolsMenu;

    // 🚨 CORREÇÃO 3: O seletor a[href='/all-products/'] falhou. Vamos usar um XPath mais específico
    // que procura o link DENTRO da DIV do menu (data-test='main-submenu').
    @FindBy(xpath = "//div[@data-test='main-submenu']//a[contains(text(), 'All products')]")
    public WebElement seeAllToolsButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}