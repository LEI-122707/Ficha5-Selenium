package sampler_5_122707.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sampler_5_122707.pages.InteractionPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class InteractionTest {

    private final InteractionPage page = new InteractionPage();

    @BeforeAll
    public static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;

    }

    @Test
    public void testDragAndDrop() {
        String path = System.getProperty("user.dir").replace("\\", "/") + "/src/test/resources/drag_and_drop_test.html";
        page.openPage("http://localhost:8000/drag_and_drop_test.html");
        page.performDragAndDrop();
        $("#droppable").shouldHave(text("Dropped!"));
    }

}





