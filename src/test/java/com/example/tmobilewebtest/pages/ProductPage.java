package com.example.tmobilewebtest.pages;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends BasePage {

    private static final String ADD_TO_BASKET_BUTTON = "button[data-qa='PRD_AddToBasket']";
    private static final String PRODUCT_AVAILABILITY_ICON = "//span[contains(@class, 'dt_badgeIcon')]//*[contains(@class, 'circle_check_list')]";
    WebDriver driver = WebDriverRunner.getWebDriver();

    public void checkProductAvailabilityAndAddToBasket() {
        logger.info(getMethodName() + "Checking product availability");
        if (isProductAvailable()) {
            clickAddToBasketButton();
        } else {
            logger.error(getMethodName() + "Product is not available");
            throw new NoSuchElementException("Product is not available");
        }
    }

    public void clickAddToBasketButton() {
        logger.info(getMethodName() + "Clicking 'Add to Basket' button using JavaScript");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement addToBasketButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ADD_TO_BASKET_BUTTON)));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", addToBasketButton);
        } catch (TimeoutException e) {
            logger.error(getMethodName() + "TimeoutException while waiting for 'Add to Basket' button: {}", e.getMessage());
        }
        logger.info(getMethodName() + "'Add to Basket' button clicked");
    }

    private boolean isProductAvailable() {
        try {
            $x(PRODUCT_AVAILABILITY_ICON).shouldBe(visible);
            logger.info(getMethodName() + "Product is available");
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public List<Integer> getPriceBeforeAddToBasket() {
        logger.info(getMethodName() + "Get price before add to basket");
        List<Integer> pricesBeforeAdding = new ArrayList<>();

        try {
            pricesBeforeAdding.add(Integer.valueOf($x("(//div[@class='sc-gzzPqb jkzHzl dt_typography variant_h4'])[5]")
                    .shouldBe(visible).getText().replaceAll("[^0-9]", "")));
            pricesBeforeAdding.add(Integer.valueOf($x("(//div[@class='sc-gzzPqb jkzHzl dt_typography variant_h4'])[6]")
                    .shouldBe(visible).getText().replaceAll("[^0-9]", "")));
            logger.info(getMethodName() + "Prices before adding to basket: {}", pricesBeforeAdding);
        } catch (NoSuchElementException e) {
            logger.error(getMethodName() + "Error getting prices before adding to basket");
        }
        return pricesBeforeAdding;
    }
}