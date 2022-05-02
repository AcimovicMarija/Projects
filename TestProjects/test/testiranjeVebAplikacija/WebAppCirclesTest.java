package testiranjeVebAplikacija;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebAppCirclesTest {
    private WebDriver browser;

    @BeforeAll
    public void openWebBrowser(){
        System.setProperty("wedriver.chrome.driver", "chrome.exe");
        browser = new ChromeDriver();
    }

    @AfterAll
    public void closeWebBrowser(){
        browser.close();
    }

    @BeforeEach
    public void reloadThePage(){
        browser.get("https://zadatak.singidunum.ac.rs/predmeti/testiranje-softvera/mouse001/");
    }

    @Test
    public void hoverEvent_ShouldDisplayShadowAroundTheCell(){
        WebElement randomElement = browser.findElements(By.cssSelector(".cells i")).get(5);

        new Actions(browser).moveToElement(randomElement).build().perform();

        String actualboxShadow = randomElement.getCssValue("box-shadow");
        String expectedBoxShadow = "rgb(34, 34, 34) 0px 0px 5px 2px";

        Assertions.assertEquals(expectedBoxShadow, actualboxShadow);
    }

    @Test
    public void clickEvent_ShouldNotChangeTheCellColour() {
        WebElement randomElement = browser.findElements(By.cssSelector(".cells i")).get(5);

        String oldValue = randomElement.getCssValue("background-color");

        new Actions(browser).moveToElement(randomElement).click().build().perform();

        String newValue = randomElement.getCssValue("background-color");

        Assertions.assertEquals(oldValue, newValue);
    }

    private void doubleClick(WebElement element){
        new Actions(browser).moveToElement(element).doubleClick().build().perform();
    }

    @Test
    public void doubleClickEvent_ShouldActivateTheCell_IfInactive_WhenTheFirstChangeOccurs() {
        WebElement randomElement = browser.findElements(By.cssSelector(".cells i")).get(5);

        doubleClick(randomElement);  // 1st - active

        String actual =  randomElement.getCssValue("background-color");
        String expected = "rgba(0, 0, 0, 1)";  // black

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void doubleClickEvent_ShouldDeActivateTheCell_IfActive_WhenTheFirstChangeOccurred() {
        WebElement randomElement = browser.findElements(By.cssSelector(".cells i")).get(5);

        doubleClick(randomElement);  // 1st - active
        doubleClick(randomElement);  // 1st - inactive

        /* II nacin umesto 2 poziva doubleClick() funkcije:
            new Actions(browser)
                .moveToElement(randomElement)
                .doubleClick()
                .doubleClick()
                .build()
                .perform();
         */

        String actual =  randomElement.getCssValue("background-color");
        String expected = "rgba(255, 255, 255, 1)"; // white

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void doubleClickEvent_ShouldActivateTheCell_IfInactive_WhenTheSecondChangeOccurs() {
        WebElement randomElement = browser.findElements(By.cssSelector(".cells i")).get(5);

        doubleClick(randomElement);  // 1st - active
        doubleClick(randomElement);  // 1st - inactive
        doubleClick(randomElement);  // 2nd - active

        String actual =  randomElement.getCssValue("background-color");
        String expected = "rgba(0, 0, 0, 1)";  // black

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void doubleClickEvent_ShouldDeActivateTheCell_IfActive_WhenTheSecondChangeOccurred() {
        WebElement randomElement = browser.findElements(By.cssSelector(".cells i")).get(5);

        doubleClick(randomElement);  // 1st - active
        doubleClick(randomElement);  // 1st - inactive
        doubleClick(randomElement);  // 2nd - active
        doubleClick(randomElement);  // 2nd - inactive

        String actual =  randomElement.getCssValue("background-color");
        String expected = "rgba(255, 255, 255, 1)";  // white

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void doubleClickEvent_ShouldActivateTheCell_IfInactive_WhenTheThirdChangeOccurs() {
        WebElement randomElement = browser.findElements(By.cssSelector(".cells i")).get(5);

        doubleClick(randomElement);  // 1st - active
        doubleClick(randomElement);  // 1st - inactive
        doubleClick(randomElement);  // 2nd - active
        doubleClick(randomElement);  // 2nd - inactive
        doubleClick(randomElement);  // 3rd - active

        String actual =  randomElement.getCssValue("background-color");
        String expected = "rgba(0, 0, 0, 1)";  // black

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void doubleClickEvent_ShouldDeActivateTheCell_IfActive_WhenTheThirdChangeOccurred() {
        WebElement randomElement = browser.findElements(By.cssSelector(".cells i")).get(5);

        doubleClick(randomElement);  // 1st - active
        doubleClick(randomElement);  // 1st - inactive
        doubleClick(randomElement);  // 2nd - active
        doubleClick(randomElement);  // 2nd - inactive
        doubleClick(randomElement);  // 3rd - active
        doubleClick(randomElement);  // 3rd - inactive

        String actual =  randomElement.getCssValue("background-color");
        String expected = "rgba(255, 255, 255, 1)";  // white

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void doubleClickEvent_ShouldNotActivateTheCell_IfInactive_WhenTheThirdChangeHasPassed() {
        WebElement randomElement = browser.findElements(By.cssSelector(".cells i")).get(5);

        doubleClick(randomElement);  // 1st - active
        doubleClick(randomElement);  // 1st - inactive
        doubleClick(randomElement);  // 2nd - active
        doubleClick(randomElement);  // 2nd - inactive
        doubleClick(randomElement);  // 3rd - active
        doubleClick(randomElement);  // 3rd - inactive
        doubleClick(randomElement);  // inactive

        String actual =  randomElement.getCssValue("background-color");
        String expected = "rgba(255, 255, 255, 1)";  // white

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void doubleClickEvent_ShouldResetAllCells_IfSevenCellsAreActive_WhenThereAreNoActiveCellsThatHaveHadThreeChanges() {
        List<WebElement> cells = browser.findElements(By.cssSelector(".cells i"));

        doubleClick(cells.get(0));  // 1st - active
        doubleClick(cells.get(1));  // 1st - active
        doubleClick(cells.get(2));  // 1st - active
        doubleClick(cells.get(3));  // 1st - active
        doubleClick(cells.get(4));  // 1st - active
        doubleClick(cells.get(5));  // 1st - active
        doubleClick(cells.get(6));  // 1st - active

        String expected = "rgba(255, 255, 255, 1)";  // white

        Assertions.assertAll(
                () -> Assertions.assertEquals(expected, cells.get(0).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(1).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(2).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(3).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(4).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(5).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(6).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(7).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(8).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(9).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(10).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(11).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(12).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(13).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(14).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(15).getCssValue("background-color"))
        );
    }

    @Test
    public void doubleClickEvent_ShouldMarkActiveThreeTimesChangedCellsAsGreen_IfSevenCellsAreActive_WhenThereAreActiveCellsThatHaveHadThreeChanges() {
        List<WebElement> cells = browser.findElements(By.cssSelector(".cells i"));

        doubleClick(cells.get(0));  // 1st - active
        doubleClick(cells.get(1));  // 1st - active
        doubleClick(cells.get(2));  // 1st - active
        doubleClick(cells.get(3));  // 1st - active
        doubleClick(cells.get(4));  // 1st - active

        doubleClick(cells.get(5));  // 1st - active
        doubleClick(cells.get(5));  // 1st - inactive
        doubleClick(cells.get(5));  // 2nd - active
        doubleClick(cells.get(5));  // 2nd - inactive
        doubleClick(cells.get(5));  // 3rd - active

        doubleClick(cells.get(6));  // 1st - active

        String expected = "rgba(255, 255, 255, 1)";  // white
        String expectedIfMarked = "rgba(0, 255, 0, 1)";  // green

        Assertions.assertAll(
                () -> Assertions.assertEquals(expected, cells.get(0).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(1).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(2).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(3).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(4).getCssValue("background-color")),
                () -> Assertions.assertEquals(expectedIfMarked, cells.get(5).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(6).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(7).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(8).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(9).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(10).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(11).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(12).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(13).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(14).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(15).getCssValue("background-color"))
        );
    }

    @Test
    public void doubleClickEvent_ShouldNotMarkAnyCellsAsGreen_IfSevenCellsAreActive_WhenThereAreNoActiveCellsThatHaveHadThreeChanges() {
        List<WebElement> cells = browser.findElements(By.cssSelector(".cells i"));

        doubleClick(cells.get(0));  // 1st - active
        doubleClick(cells.get(1));  // 1st - active
        doubleClick(cells.get(2));  // 1st - active
        doubleClick(cells.get(3));  // 1st - active
        doubleClick(cells.get(4));  // 1st - active

        doubleClick(cells.get(5));  // 1st - active
        doubleClick(cells.get(5));  // 1st - inactive
        doubleClick(cells.get(5));  // 2nd - active
        doubleClick(cells.get(5));  // 2nd - inactive
        doubleClick(cells.get(5));  // 3rd - active
        doubleClick(cells.get(5));  // 3rd - inactive

        doubleClick(cells.get(6));  // 1st - active
        doubleClick(cells.get(7));  // 1st - active

        String expected = "rgba(255, 255, 255, 1)";  // white

        Assertions.assertAll(
                () -> Assertions.assertEquals(expected, cells.get(0).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(1).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(2).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(3).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(4).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(5).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(6).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(7).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(8).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(9).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(10).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(11).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(12).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(13).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(14).getCssValue("background-color")),
                () -> Assertions.assertEquals(expected, cells.get(15).getCssValue("background-color"))
        );
    }
}
