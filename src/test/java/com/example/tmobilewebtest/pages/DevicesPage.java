package com.example.tmobilewebtest.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class DevicesPage extends BasePage {

    private static final String PRODUCT_CARD = ".styles__StyledCards-sc-176tmlw-0.bqnsKT [data-qa^='LST_ProductCard']";

    public void selectFirstProduct(int index) {
        logger.info(getMethodName() + "Selecting product at index {}", index);
        ElementsCollection productCards = $$(PRODUCT_CARD);

        if (index >= 0 && index < productCards.size()) {
            productCards.get(index).click();
            logger.info(getMethodName() + "Clicked on product card at index {}", index);
        } else {
            logger.error(getMethodName() + "Error selecting product: Invalid index {}", index);
        }
        logger.info(getMethodName() + "Product select at index {}", index);
    }
}
