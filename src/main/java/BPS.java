import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BPS {

    public static void main (String [] arg){

        //System.setProperty("webdriver.chrome.driver","Z:\\QA\\sel\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver= new ChromeDriver();
        driver.get("https://www.bps-sberbank.by");
        firsTest(driver);
        driver.quit();
    }

    public static void firsTest(WebDriver driver) {
     MainMenu mainMenu = new MainMenu(driver.findElement(By.id("app")),driver);
     DropDownMenu depositMenu= mainMenu.selectDeposite();
     depositMenu.clickToHeader("В ИНОСТРАННОЙ ВАЛЮТЕ");
     DepositePage depositePage = new DepositePage(driver.findElement(By.id("app")),driver);

        System.out.println("sdf");
    }


}
