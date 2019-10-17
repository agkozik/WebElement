import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DepositePage extends Element {

    List<DepositResult> depositResults = new ArrayList<>();

    public DepositePage(WebElement webElement, WebDriver driver) {
        super(webElement, driver);
       List<WebElement> dp= webElement.findElements(By.className("DepositesSelectionResult"));
        for (WebElement result:dp) {
            depositResults.add(new DepositResult(result,driver));
        }
    }
}
