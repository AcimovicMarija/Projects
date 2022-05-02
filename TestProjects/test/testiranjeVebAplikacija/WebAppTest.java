package testiranjeVebAplikacija;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebAppTest {
    private WebDriver browser;

    @BeforeAll
    public static void setWebDriverPath() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeEach
    public void prepareWebDriver() {
        browser = new ChromeDriver();
    }

    @AfterEach
    public void closeWebBrowser(){
        browser.close();
    }

    @Test
    public void buttonClick_ShouldPlace37750InTheOutputElement_IfTheInputElementHas50000(){
        browser.get("http://zadatak.singidunum.ac.rs/predmeti/testiranje-softvera/primer001/");

        browser.findElement(By.id("bruto")).clear();
        browser.findElement(By.id("bruto")).sendKeys("50000");

        browser.findElement(By.cssSelector("#kalkulator-plate > button")).click();

        String actual = browser.findElement(By.id("neto")).getText();
        String expected = "37750";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void buttonClick_ShouldPlace15000InTheOutputElement_IfTheInputElementHas15000(){
        browser.get("http://zadatak.singidunum.ac.rs/predmeti/testiranje-softvera/primer001/");

        browser.findElement(By.id("bruto")).clear();
        browser.findElement(By.id("bruto")).sendKeys("15000");

        browser.findElement(By.cssSelector("#kalkulator-plate > button")).click();

        String actual = browser.findElement(By.id("neto")).getText();
        String expected = "15000";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void buttonClick_ShouldPlace10000InTheOutputElement_IfTheInputElementHas10000(){
        browser.get("http://zadatak.singidunum.ac.rs/predmeti/testiranje-softvera/primer001/");

        browser.findElement(By.id("bruto")).clear();
        browser.findElement(By.id("bruto")).sendKeys("10000");

        browser.findElement(By.cssSelector("#kalkulator-plate > button")).click();

        String actual = browser.findElement(By.id("neto")).getText();
        String expected = "10000";

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ApplicationLoad_ShouldPlaceTheLastUsedBrutValueInTheInputField(){
        browser.get("http://zadatak.singidunum.ac.rs/predmeti/testiranje-softvera/primer001/");

        browser.findElement(By.id("bruto")).clear();
        browser.findElement(By.id("bruto")).sendKeys("80000");
        browser.findElement(By.cssSelector("#kalkulator-plate > button")).click();

        browser.get("http://zadatak.singidunum.ac.rs/predmeti/testiranje-softvera/primer001/");
        String actual = browser.findElement(By.id("bruto")).getAttribute("value");
        String expected = "80000";

        Assertions.assertEquals(expected, actual);
    }
}
