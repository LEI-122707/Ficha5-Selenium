package iscteiul.sta.ficha5selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Page Object Model
public class MainPage {

    // Botão de Cookies (Mantido, pois funcionou)
    @FindBy(xpath = "//button[contains(text(), 'Accept All')]")
    public WebElement acceptCookiesButton;

    // Ícone de pesquisa (Mantido, pois o clique inicial funcionou)
    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    // 🚨 CORRIGIDO: Volta ao seletor data-test, mas o teste será corrigido para o encontrar
    @FindBy(css = "[data-test='search-input']")
    public WebElement searchInput;

    // Menu "Developer Tools" (Mantido, pois o clique inicial funcionou)
    @FindBy(xpath = "//button[contains(., 'Developer Tools')]")
    public WebElement developerToolsMenu;

    // 🚨 CORRIGIDO: XPath mais robusto para o link "All products", ignorando a div do submenu
    @FindBy(xpath = "//a[normalize-space(text())='All products' and contains(@href, '/all-products/')]")
    public WebElement seeAllToolsButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}