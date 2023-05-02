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

class TestWebFormNegative {
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

    //@Disabled
    @Test
    void shouldTestV1() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Valerko Mikhail");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestV3() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил1");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestV4() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Mихаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestV5() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("˜Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestV2() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+779012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestV6() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7912423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void shouldTestV7() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+790364");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestV8() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+72903i23264");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Disabled
    @Test
    void shouldTestV9() {
        driver.get("http://0.0.0.0:7777/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+19012345678");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

}