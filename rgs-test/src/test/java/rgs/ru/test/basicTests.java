package rgs.ru.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main (String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver  driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.navigate().to("https://www.rgs.ru/");
        try {

            // Кликаем на меню
            clickField("//body//div[@id=\"main-navbar-collapse\"]//a[contains(text(), 'Меню')]", driver);

            // Кликаем на элемен тменю "Компаниям"
            clickField("//body//div[@id='main-navbar-collapse']//a[contains(text(),'Компаниям')]", driver);

            // Скролим до здоровья и кликаем
            WebElement health = driver.findElement(By.xpath("//div[contains(@class,'col-rgs-content-center-col')]//a[contains(text(),'здоровь')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", health);
            health.click();

            // Так как открылось новое окно, переводим туда драйвер
            ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(1));

            // Кликаем на страхование
            clickField("//*[@id=\"main-content\"]/div[3]/div/div[1]/div/div/a[1]", driver);

            // Проверяем наличие заголовка "Добровольное медицинское страхование"
            try {
                driver.findElement(By.xpath("//div[contains(@class,'col-rgs-content-center')]" +
                        "//h1[contains(text(),'Добровольное медицинское страхование')]"));
            } catch (NoSuchElementException e) {
                System.out.println("No such element: Добровольное медицинское страхование");
            }

            // Жмем кнопку "Отправить заявку"
            clickField("//div[contains(@class,'rgs-context-bar')]//a[contains(text(),'тправить заявку')]", driver);

            /* Заполняем все поля */
            // Ловим Поп-ап
//            Thread.sleep(5000);
            try {
                clickField("/html/body/div/div[11]/button", driver);
            } catch (org.openqa.selenium.NoSuchElementException e) {
                e.getMessage();
            }
            // Ф.И.О
            fillField("//*[@id=\"applicationForm\"]/div[2]/div[1]/input", "Фамилия", driver);
            fillField("//*[@id=\"applicationForm\"]/div[2]/div[2]/input", "Имя", driver);
            fillField("//*[@id=\"applicationForm\"]/div[2]/div[3]/input", "Отчество", driver);

            // Регион
            fillField("//*[@id=\"applicationForm\"]/div[2]/div[4]/select", "Москва", driver);

            // Номер
            clickAndFillField("//*[@id=\"applicationForm\"]/div[2]/div[5]/input", "9999999999", driver);

            // Почта
            fillField("//*[@id=\"applicationForm\"]/div[2]/div[6]/input", "qwertyqwerty", driver);

            // Дата
            fillField("//*[@id=\"applicationForm\"]/div[2]/div[7]/input", "12.08.2021", driver);

            // Комментарий
            fillField("//*[@id=\"applicationForm\"]/div[2]/div[8]/textarea", "Comment", driver);

            // Я согласен на обработку данных
            clickField("//*[@id=\"applicationForm\"]/div[2]/div[9]", driver);

            // Кнопка Отправить
            clickField("//*[@id=\"button-m\"]", driver);

            // Првоеряем у поля почты ошибку
            try {
                driver.findElement(By.xpath("//*[@id=\"applicationForm\"]/div[2]/div[6]/div/label/span"));
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.out.println("No email Error");
            }
        } catch (InvalidSelectorException e) {
            System.out.println("Bad xpath");
        } finally {
            Thread.sleep(5000);
            driver.quit();
        }
    }
    public static void clickField (String xpath, WebDriver driver) {
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }

    public static void fillField (String xpath, String str, WebDriver driver) {
        WebElement element = driver.findElement(By.xpath(xpath));
        element.sendKeys(str);
    }

    public  static void clickAndFillField (String xpath, String str, WebDriver driver) {
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        element.sendKeys(str);
    }
}
