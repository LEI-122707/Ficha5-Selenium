package org.example.ficha5;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    // Elementos agora são privados e usam seletores CSS (mais limpo que XPath)
    private final SelenideElement developerToolsBtn = $("[data-test-marker='Developer Tools']");
    private final SelenideElement suggestionLink = $("[data-test='suggestion-link']");
    private final SelenideElement toolsMenuBtn = $("[aria-label='Developer Tools: Open submenu']");
    private final SelenideElement searchIcon = $("[data-test='site-header-search-action']");
    private final SelenideElement searchField = $("[data-test-id='search-input']");
    private final SelenideElement cookieButton = $("button.ch2-allow-all-btn");

    // Método novo para lidar com cookies aqui dentro
    public void acceptCookies() {
        if (cookieButton.isDisplayed()) {
            cookieButton.click();
        }
    }

    public void openDevTools() {
        developerToolsBtn.click();
    }

    public void selectFindYourTools() {
        suggestionLink.click();
    }

    public void toggleMenu() {
        toolsMenuBtn.click();
    }

    public void openSearch() {
        searchIcon.click();
    }

    // Método que combina digitar e validar que está visível
    public void typeInSearch(String text) {
        searchField.shouldBe(visible).setValue(text);
    }

    // Getter para validação no teste, mantendo o elemento privado
    public SelenideElement getSearchInput() {
        return searchField;
    }
}