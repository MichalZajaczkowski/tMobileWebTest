package com.example.tmobilewebtest.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {
    private static final String T_MOBILE_URL = "https://www.t-mobile.pl";
    private static final String DEVICES = "//button[contains(text(), 'Urządzenia')]";
    private static final String COOKIE_ACCEPT_BUTTON_ID = "didomi-notice-agree-button";
    private static final String SMARTWATCHES_AND_WRISTBANDS_SECTION = "//p[contains(text(), 'Smartwatche i opaski')]/following-sibling::ul";
    private static final String NO_SUBSCRIPTION_BUTTON = ".//span[contains(text(), 'Bez abonamentu')]";

    public void openHomePage() {
        logger.info(getMethodName() + "Opening T-Mobile home page");
        try {
            openURL(T_MOBILE_URL);
        } catch (Exception e) {
            logger.error(getMethodName() + "Error opening T-Mobile home page: {}", e.getMessage());
        }
    }

    public void goToDevicesPage() {
        logger.info(getMethodName() + "Navigating to 'Urządzenia'");
        SelenideElement devices = $x(DEVICES);

        if (devices.exists()) {
            devices.hover();
            logger.info(getMethodName() + "Hovered over 'Urządzenia'");
        } else {
            logger.error(getMethodName() + "Error while navigating to 'Urządzenia': Element not found");
        }
    }

    public void chooseNoSubscribeFromSmartwatchesAndWristbands() {
        logger.info(getMethodName() + "Selecting 'Bez abonamentu' from 'Smartwatche i opaski' section");
        try {
            SelenideElement smartwatchesAndWristbands = $x(SMARTWATCHES_AND_WRISTBANDS_SECTION);
            SelenideElement noSubscription = smartwatchesAndWristbands.$x(NO_SUBSCRIPTION_BUTTON);
            noSubscription.shouldBe(Condition.visible).click();
        } catch (Exception e) {
            logger.error(getMethodName() + "Error choosing 'Bez abonamentu' from 'Smartwatche i opaski' section: {}", e.getMessage());
        }
    }

    public void acceptCookies() {
        logger.info(getMethodName() + "Accepting cookies");
        try {
            $("#" + COOKIE_ACCEPT_BUTTON_ID).shouldBe(Condition.visible).click();
        } catch (Exception e) {
            logger.error(getMethodName() + "Error accepting cookies: {}", e.getMessage());
        }
    }
}


