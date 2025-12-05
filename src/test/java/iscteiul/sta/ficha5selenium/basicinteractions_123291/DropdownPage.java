package iscteiul.sta.ficha5selenium.basicinteractions_123291; // ⚠️ Substitui TEUNUMERO pelo teu nº

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DropdownPage {

    private static final String URL = "https://the-internet.herokuapp.com/dropdown";

    // O elemento principal da lista (select)
    private final SelenideElement dropdownList = $("#dropdown");

    // Um seletor inteligente para encontrar qual opção está selecionada no momento
    private final SelenideElement selectedOption = $("#dropdown option:checked");

    public void openPage() {
        open(URL);
    }

    // Método para selecionar pelo texto visível (ex: "Option 1")
    public void selectByText(String text) {
        dropdownList.selectOption(text);
    }

    // Método para o teste verificar o que está selecionado
    public SelenideElement getSelectedOption() {
        return selectedOption;
    }
}