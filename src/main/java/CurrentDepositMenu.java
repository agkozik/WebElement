import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CurrentDepositMenu extends Element {

    List<WebElement> items;

    public CurrentDepositMenu(WebElement webElement, WebDriver driver) {
        super(webElement, driver);
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.className("BlockHeaderProduct")));
        this.webElement = webElement.findElement(By.xpath("//li[@class='depo_elem']"));
        items = this.webElement.findElements(By.xpath("//li[@class='depo_elem']"));
        System.out.println("Quantity of menu items " + items.size() + " " + items.get(2).getText());
    }

    public void chooseMenu(String menu) {
        for (WebElement ind : items) {
            if (ind.getText().contains(menu)) {
                ind.click();
            }
        }
    }

    public void ChangeCurrency(String currencyCode) {

        chooseMenu("ВАЛЮТА");

        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.className("SelectorDropDown__selector__list")));
        List<WebElement> menuCurrency = webDriver.findElements(By.className("SelectorDropDown__selector__list_elem_icon"));

        System.out.println("Quantity of Валюта " + menuCurrency.size() + menuCurrency.get(2).getText());

        switch (currencyCode) {
            case "EUR":
                menuCurrency.get(0).click();
                break;
            case "USD":
                menuCurrency.get(1).click();
                break;
            case "RUB":
                menuCurrency.get(2).click();
                break;
            default:
                System.out.println("Currency not found");
        }
    }


}
