package iscteiul.sta.ficha5selenium.basicinteractions_122692;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object para a página de Autenticação (Form Authentication)
 * URL: https://the-internet.herokuapp.com/login
 */
public class AuthPage {

    private WebDriver driver;

    // Campo de input para o nome de utilizador (username)
    @FindBy(id = "username")
    public WebElement usernameInput;

    // Campo de input para a palavra-passe (password)
    @FindBy(id = "password")
    public WebElement passwordInput;

    // Botão de Login
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    // Mensagem de status (sucesso ou erro)
    @FindBy(id = "flash")
    public WebElement statusMessage;

    // Construtor: Inicializa os elementos da página
    public AuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Método de Ação: Executa o processo de login
     */
    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}