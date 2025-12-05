package iscteiul.sta.ficha5selenium.sampler7;

import com.codeborne.selenide.Configuration;
import iscteiul.sta.ficha5selenium.sampler7.pages.DataPresentationPage;
import iscteiul.sta.ficha5selenium.sampler7.pages.SamplerHomePage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Sampler7Test {

    @BeforeEach
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

    @Test
    @Description("Aceder à secção Data Presentation e validar o carregamento da grelha")
    public void testDataPresentationGrid() {

        SamplerHomePage home = new SamplerHomePage().open();

        home.navigateToDataPresentation();

        DataPresentationPage page = new DataPresentationPage();
        page.switchToExample();
        page.gridShouldLoadData();
    }
}
