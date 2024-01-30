package com.example.tmobilewebtest.pages;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {
    protected Logger logger = LogManager.getLogger(this.getClass());

    protected void openURL(String url) {
        WebDriverRunner.getWebDriver().navigate().to(url);
    }

    protected String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName() + ": ";
    }
}
