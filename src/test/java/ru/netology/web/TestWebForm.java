package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestWebForm {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:7777");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestV1() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestV2() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("валерко михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestV3() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+89012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestV4() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+89012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestV5() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Михаил валерко");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+89012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestV6() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("михаил Валерко");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+89012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        assertEquals(expected, actual);
    }

}

