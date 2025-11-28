package iscteiul.sta.ficha5selenium.basicinteractions_122692;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * Page Object para a página de Checkboxes
 * URL: https://the-internet.herokuapp.com/checkboxes
 */
public class CheckboxPage {

    private WebDriver driver;

    // Lista de todas as caixas de seleção na página
    // O localizador CSS 'input[type="checkbox"]' é genérico e robusto.
    @FindBy(css = "input[type='checkbox']")
    public List<WebElement> checkboxes;

    // Construtor: Inicializa os elementos da página
    public CheckboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Método de Ação: Clica na caixa de seleção pelo seu índice (0 para o primeiro, 1 para o segundo).
     */
    public void clickCheckbox(int index) {
        if (index >= 0 && index < checkboxes.size()) {
            checkboxes.get(index).click();
        } else {
            throw new IndexOutOfBoundsException("Índice de checkbox inválido: " + index);
        }
    }
}