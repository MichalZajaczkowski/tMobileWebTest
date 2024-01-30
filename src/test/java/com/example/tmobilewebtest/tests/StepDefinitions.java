package com.example.tmobilewebtest.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.tmobilewebtest.pages.BasketPage;
import com.example.tmobilewebtest.pages.DevicesPage;
import com.example.tmobilewebtest.pages.HomePage;
import com.example.tmobilewebtest.pages.ProductPage;
import com.example.tmobilewebtest.utils.DriverFactory;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.Assert;

public class StepDefinitions {

    HomePage homePage = new HomePage();
    DevicesPage devicesPage = new DevicesPage();
    ProductPage productPage = new ProductPage();
    BasketPage basketPage = new BasketPage();

    @BeforeAll
    public static void setupClass() {
        WebDriverRunner.setWebDriver(DriverFactory.getDriver("chrome"));
        Configuration.reportsFolder = "logs/bugScreen";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Given("I open the T-Mobile homepage")
    public void iOpenTheTMobileHomepage() {
        homePage.openHomePage();
    }

    @And("I accept cookies if they appear")
    public void iAcceptCookiesIfTheyAppear() {
        homePage.acceptCookies();
    }

    @When("I go to Devices from the top bar")
    public void iGoToDevicesFromTheTopBar() {
        homePage.goToDevicesPage();
    }

    @And("I select \"No subscription\" from the 'Smartwatches and Wristbands' column")
    public void iSelectNoSubscriptionFromTheSmartwatchesAndWristbandsColumn() {
        homePage.chooseNoSubscribeFromSmartwatchesAndWristbands();
    }

    @And("I click on the first item from the list")
    public void iClickOnTheFirstItemFromTheList() {
        devicesPage.selectFirstProduct(0);
    }

    @And("I get the prices of the product")
    public void iGetThePricesOfTheProduct() {
        productPage.getPriceBeforeAddToBasket();
    }

    @And("I add the device to the basket")
    public void iAddTheDeviceToTheBasket() {
        productPage.checkProductAvailabilityAndAddToBasket();
    }

    @Then("I get the prices of the product after adding to the basket")
    public void iGetThePricesOfTheProductAfterAddingToTheBasket() {
        basketPage.getPriceAfterAddToBasket();
    }

    @And("I compare prices before and after adding to the basket")
    public void iComparePricesBeforeAndAfterAddingToTheBasket() {

    }

    @And("I return to the T-Mobile home page")
    public void iReturnToTheTMobileHomePage() {
        homePage.openHomePage();
    }

    @And("I check and compare the basket after adding the product")
    public void iCheckAndCompareTheBasketAfterAddingTheProduct() {
        boolean isAdded = basketPage.isProductAddedToBasket();
        Assert.assertTrue("Produkt nie został dodany do koszyka", isAdded);
    }

    @AfterAll
    public static void tearDown() {
        WebDriverRunner.closeWebDriver();
    }
}
