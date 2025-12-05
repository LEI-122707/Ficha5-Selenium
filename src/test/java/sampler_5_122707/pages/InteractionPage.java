package sampler_5_122707.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.interactions.Actions;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

public class InteractionPage {

    private final SelenideElement draggable = $("#draggable");
    private final SelenideElement droppable = $("#droppable");

    public void openPage(String filePath) {
        open(filePath); // já passamos "file:///" no teste
        draggable.shouldBe(visible);
        droppable.shouldBe(visible);
    }

    public void performDragAndDrop() {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.dragAndDrop(draggable, droppable).perform();
    }
}








