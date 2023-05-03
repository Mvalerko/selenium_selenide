package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
    void negativeScriptNameInEnglish() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Valerko Mikhail");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptNameOmitted() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptSpaceInsteadOfName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys(" ");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptNameAlongWithNumber() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил1");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptNameAlongWithEnglishLetter() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Mихаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptNameAlongWithSpecialSymbol() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("˜Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptPhoneOfGreaterLength() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+779012423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptPhoneOfShorterLength() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7912423764");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptPhoneOmitted() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void negativeScriptSpaceInsteadOfPhone() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys(" ");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void negativeScriptPhoneOfShorterLengthV2() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+790364");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptPhoneAlongWithEnglishLetter() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+72903i23264");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptPhoneAlongWithRussianLetter() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+72903я23264");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptPhoneInWrongFormat() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("19263436587");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void negativeScriptNoAgreement() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Валерко Михаил");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+72903123264");
        //driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("[data-test-id='agreement'] span.checkbox__text")).getText().trim();
        assertEquals(expected, actual);
    }
}