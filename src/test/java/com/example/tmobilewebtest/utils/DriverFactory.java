package com.example.tmobilewebtest.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    public static WebDriver getDriver(String browser) {
        logger.info("Get driver: {}", browser);
        try {
            WebDriver driver = switch (browser) {
                case "chrome" -> new ChromeDriver();
                case "firefox" -> new FirefoxDriver();
                case "edge" -> new EdgeDriver();
                default -> throw new IllegalArgumentException("Unknown browser: " + browser);
            };

            configureDriver(driver); // Konfiguracja drivera

            return driver;
        } catch (IllegalArgumentException e) {
            logger.error("Error getting driver: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    private static void configureDriver(WebDriver driver) {
        driver.manage().window().maximize();
    }
}


