import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainMenu extends Element {

    List<WebElement> items ;


    public MainMenu(WebElement webElement,WebDriver driver) {
        super(webElement.findElement(By.className("main-menu")),driver);
        items= this.webElement.findElements(By.className("main-menu__nav-item-inner"));
    }

    private DropDownMenu getMenu(Integer index){
        items.get(index).click();
        return new DropDownMenu(webDriver.findElement(By.id("app")),webDriver);
    }

    public DropDownMenu selectCard()
    {
        return getMenu(1);
    }

    public DropDownMenu selectDeposite()
    {
        return getMenu(2);
    }

    public DropDownMenu selectCredit()
    {
        return getMenu(3);
    }

    public DropDownMenu selectPayAndTrasfer()
    {
        return getMenu(4);
    }

}
