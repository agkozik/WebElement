import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DepositForeignCurrency extends Element {

    List<WebElement> items;

    public DepositForeignCurrency(WebElement webElement, WebDriver driver) {
        super(webElement, driver);
        new WebDriverWait(webDriver,30).until(ExpectedConditions.visibilityOfElementLocated(By.className("DepositesSelectionResult")));
        //this.webElement= webElement.findElement(By.className("DepositesSelectionResult"));
        items = this.webDriver.findElements(By.className("DepositesSelectionResult"));
        System.out.println("Quantity of foreign deposits "+items.size());
    }

    public void clickOnDeposit(Integer index){
        new WebDriverWait(webDriver,30).until(ExpectedConditions.elementToBeClickable(By.className("DepositesSelectionResult__buttons")));
        System.out.println("Click to deposit number "+index+" - "+items.get(index).getAttribute("class"));
        items.get(index).findElement(By.className("DepositesSelectionResult__buttons")).click();
    }
}
