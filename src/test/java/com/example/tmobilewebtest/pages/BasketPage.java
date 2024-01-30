package com.example.tmobilewebtest.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BasketPage extends BasePage {
    private static final String TOTAL_UP_FRONT = "(//span[@data-qa='BKT_TotalupFront'])[1]";
    private static final String TOTAL_MONTHLY = "(//span[@data-qa='BKT_TotalMonthly'])[1]";
    private int initialProductCount = 0;
    private int newProductCount = 0;

    public List<Integer> getPriceAfterAddToBasket() {
        logger.info(getMethodName() + "Get price after add to basket");
        List<Integer> pricesAfterAdding = new ArrayList<>();
        try {
            pricesAfterAdding.add(Integer.valueOf($x(TOTAL_UP_FRONT).getText().replaceAll("[^0-9]", "")));
            pricesAfterAdding.add(Integer.valueOf($x(TOTAL_MONTHLY).getText().replaceAll("[^0-9]", "")));

        } catch (NoSuchElementException e) {
            logger.error(getMethodName() + "Error adding price to the list: {}", e.getMessage());
        }
        logger.info(getMethodName() + "Prices after adding to basket: {}", pricesAfterAdding);
        return pricesAfterAdding;
    }

    public boolean isProductAddedToBasket() {
        logger.info(getMethodName() + "Check is product is added to basket");
        try {
            int currentProductCount = getProductCountFromBasket();
            boolean isAdded = currentProductCount > initialProductCount;

            if (isAdded) {
                initialProductCount = currentProductCount;
                newProductCount = currentProductCount;
                logger.info(getMethodName() + "Product added to basket. New product count: {}", newProductCount);
            }

            return isAdded;
        } catch (Exception e) {
            logger.error(getMethodName() + "Error checking if product is added to basket: {}", e.getMessage());
            return false;
        }
    }

    private int getProductCountFromBasket() {
        String productCountText = $(".rounded-full").getText().replaceAll("[^0-9]", "");

        if (productCountText.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(productCountText);
    }
}
