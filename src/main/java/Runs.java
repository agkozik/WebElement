import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class Runs {

    public static void main( String[] args)  {

        System.setProperty("webdriver.chrome.driver","Z:\\QA\\sel\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver= new ChromeDriver();
        driver.get("http://taxi.yandex.by");
        WebElement from=  driver.findElement(By.id("addressFrom"));
        from.sendKeys("улица Пушкина, 2");
        selectFirstValueFromPopup(driver);
        WebElement to= driver.findElement(By.id("addressTo"));
        to.sendKeys("Речицкий проспект");
        selectFirstValueFromPopup(driver);
        WebElement serviceLevel=driver.findElement(By.id("routestats"));
        String typeService=serviceLevel.getText();
        String priceComfort="";
        if(!typeService.contains("Комфорт")){
            priceComfort=getPriceFromType("Комфорт",serviceLevel,driver);
        }
        if(priceComfort.isEmpty()){
            throw  new RuntimeException("komfort is not found");
        }
        String economePrice=getPriceFromType("Эконом",serviceLevel,driver);
        priceComfort=priceComfort
                .substring(priceComfort.indexOf("—")+1,priceComfort.indexOf("р")-1)
                .replace(",",".");
        economePrice=economePrice.substring(economePrice.indexOf("—")+1,economePrice.indexOf("р")-1).replace(",",".");
        if(Double.valueOf(priceComfort)<=Double.valueOf(economePrice)){
            throw  new RuntimeException("price comfort is less than econome");
        }

        driver.quit();
    }


//
//    WebElement serviceLevel=driver.findElement(By.id("routestats"));
//    String typeService=serviceLevel.getText();
//    String priceComfort="";
//        if(!typeService.contains("Комфорт")){
//        priceComfort=getPriceFromType("Комфорт",serviceLevel,driver);
//    }
//        if(priceComfort.isEmpty()){
//        throw  new RuntimeException("komfort is not found");
//    }
//    String economePrice=getPriceFromType("Эконом",serviceLevel,driver);
//    priceComfort=priceComfort.substring(priceComfort.indexOf("—")+1,priceComfort.indexOf("р")-1).replace(",",".");
//    economePrice=economePrice.substring(economePrice.indexOf("—")+1,economePrice.indexOf("р")-1).replace(",",".");
//        if(Double.valueOf(priceComfort)<=Double.valueOf(economePrice)){
//        throw  new RuntimeException("price comfort is less than econome");



        public static String getPriceFromType(String type,WebElement serviceLevel,WebDriver driver){
        serviceLevel.click();
        WebElement popup=selectPopup(driver);
        List<WebElement> items= popup.findElements(By.className("select__item"));
        for (WebElement item: items) {
            if(item.getText().contains(type)){
                item.click();
                new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.visibilityOfElementLocated(By.className("routestats__price")));
                return driver.findElement(By.className("routestats__price")).getText();
            }
        }
        return "";
    }


     public static void selectFirstValueFromPopup(WebDriver driver){
        selectPopup(driver)
             .findElement(By.className("b-autocomplete-item"))
             .click();
     }

    public static WebElement selectPopup(WebDriver driver){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("popup_visibility_visible")));
        return driver.findElement(By.className("popup_visibility_visible"));
    }



}
