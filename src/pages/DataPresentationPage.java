package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class DataPresentationPage {

    public SelenideElement exampleFrame =
            $("iframe.vaadin-sampler-frame");

    public void switchToExample() {
        switchTo().frame(exampleFrame);
    }

    public SelenideElement grid =
            $("vaadin-grid");

    public void gridShouldLoadData() {
        grid.should(exist).shouldBe(visible);

        // Pelo menos 1 linha carregada
        $$("vaadin-grid-cell-content")
                .first()
                .shouldBe(visible);
    }
}


