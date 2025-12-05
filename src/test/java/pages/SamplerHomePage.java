package pages;

import static com.codeborne.selenide.Selenide.*;

public class SamplerHomePage {

    private final String baseUrl = "https://demo.vaadin.com/sampler/";

    public SamplerHomePage open(String baseUrl) {
        open(this.baseUrl);
        return this;
    }

    // Link do menu "Data Presentation"
    public void navigateToDataPresentation() {
        $x("//span[text()='Data']").click();
        $x("//span[text()='Data Presentation']").click();
    }
}

