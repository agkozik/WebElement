import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BPS {

    public static void main(String[] arg) throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver","Z:\\QA\\sel\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bps-sberbank.by");
        firsTest(driver);
        Thread.sleep(2000);
        driver.quit();
    }

    public static void firsTest(WebDriver driver) {
        MainMenu mainMenu = new MainMenu(driver.findElement(By.id("app")), driver);
        DropDownMenu depositMenu = mainMenu.selectDeposite();

        depositMenu.clickToHeader("В ИНОСТРАННОЙ ВАЛЮТЕ");

        DepositForeignCurrency depositForeignCurrency = new DepositForeignCurrency(driver.findElement(By.id("app")), driver);
        depositForeignCurrency.clickOnDeposit(1);

        CurrentDepositMenu currentDepositMenu = new CurrentDepositMenu(driver.findElement(By.id("app")), driver);
        currentDepositMenu.ChangeCurrency("USD");

        SimpleCalculator simpleCalculator = new SimpleCalculator(driver.findElement(By.id("app")), driver);
        simpleCalculator.useDepositCalculatorBinary(100,"10000");
        simpleCalculator.useDepositCalculator(100,"10000");

        System.out.println("sdf");
    }


}
