import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SimpleCalculator extends Element {

    static List<WebElement> itemsCalc;

    public SimpleCalculator(WebElement webElement, WebDriver driver) {

        super(webElement, driver);
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.className("SimpleCalculator__wrap--flex")));
        itemsCalc = this.webElement.findElements(By.className("InputSlider__wrap"));
    }

    public void useDepositCalculator(int profit, String sum) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", itemsCalc.get(0));
WebElement buttonMore = webDriver.findElement(By.id("ButtonDefault_27"));
buttonMore.click();
List<WebElement> reservItems = webDriver.findElements(By.className("value"));

        Actions actions = new Actions(webDriver);
        int lowBoundary = 90;
        int highBoundary = 1000;
        int currentValue = (highBoundary - lowBoundary) / 2;

        //Linear search
//        for(int i=90;i<=1000;i++)   {
//
//            actions.doubleClick(itemsCalc.get(0)).sendKeys(String.valueOf(i)).build().perform();
//            new WebDriverWait(webDriver,15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='SimpleCalculator__result--income']/span")));
//            actions.doubleClick(itemsCalc.get(1)).sendKeys(sum).build().perform();
//
//            WebElement currentProfit = webElement.findElement(By.xpath("//div[@class='SimpleCalculator__result--income']/span"));
//            Integer resultProfit = Integer.valueOf((currentProfit.getText()).substring(0, currentProfit.getText().indexOf(",")));
//
//            if(resultProfit==profit) {
//                System.out.println("Чтобы получить " + profit + " y.e нужно " + sum + " и " + currentValue + " дней.");
//                break;
//            }
//        }
        //Binary search
        WebElement currentProfit;
        Integer resultProfit;
        int i = 0;

        while (lowBoundary<=highBoundary) {
            i++;
            System.out.println("Цикл FOR - итерация " + i );
            System.out.println("\t currentValue " + currentValue);

            actions.doubleClick(itemsCalc.get(0)).sendKeys(String.valueOf(currentValue)).build().perform();
            actions.doubleClick(reservItems.get(0)).sendKeys(String.valueOf(currentValue)).build().perform();//дублирование кода из-за перекрытия ползунком области клика

            new WebDriverWait(webDriver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='SimpleCalculator__result--income']/span")));
            actions.doubleClick(itemsCalc.get(1)).sendKeys(sum).build().perform();

            currentProfit = webElement.findElement(By.xpath("//div[@class='SimpleCalculator__result--income']/span"));
            resultProfit = Integer.valueOf((currentProfit.getText()).substring(0, currentProfit.getText().indexOf(",")));

            if (resultProfit > profit) {
                highBoundary = currentValue;
                currentValue = (((highBoundary - lowBoundary) / 2) + lowBoundary);
                System.out.println("lowBoundary="+lowBoundary+"\t currentValue= " + currentValue + "\t\t highBoundary= "  + highBoundary + "\t\t resultProfit= "+ resultProfit);

            }

            if (resultProfit < profit) {
                lowBoundary = currentValue;
                currentValue = (((highBoundary - lowBoundary) / 2) + lowBoundary);
                System.out.println("lowBoundary="+lowBoundary+"\t currentValue= " + currentValue + "\t\t highBoundary= "  + highBoundary + "\t\t resultProfit= "+ resultProfit);

            }

            if (resultProfit == profit) {
                System.out.println("Чтобы получить " + profit + " y.e нужно " + sum + " и " + currentValue + " дней.");
                break;
            }
        }
    }

}
