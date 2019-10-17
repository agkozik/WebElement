import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class DropDownMenu extends Element {

    List<WebElement> headers;


    public DropDownMenu(WebElement webElement, WebDriver driver) {
        super(webElement,driver);
        new WebDriverWait(webDriver,10).until(ExpectedConditions.visibilityOfElementLocated(By.className("nav_category-drop-down")));
        this.webElement= webElement.findElement(By.className("nav_category-drop-down"));
        headers= this.webElement.findElements(By.className("nav__item_level-0"));
    }


    public WebElement clickToHeader(String headerText){
        for (WebElement el :headers ) {
           if(el.getText().equalsIgnoreCase(headerText)){
               el.click();
               return webDriver.findElement(By.id("app"));
           }
        }
        return null;
    }

}
