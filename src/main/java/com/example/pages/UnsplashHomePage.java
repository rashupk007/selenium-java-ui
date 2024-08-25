package com.example.pages;

import com.example.framework.utils.ui.SeleniumWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UnsplashHomePage {

  private SeleniumWrapper seleniumWrapper;

  @FindBy(css = "input[data-test='homepage-header-search-form-input']")
  private WebElement searchInput;

  @FindBy(css = "button[data-test='homepage-header-search-form-button']")
  private WebElement searchButton;

  @FindBy(css = "a[data-test='nav-bar-editorial-link']")
  private WebElement exploreLink;

  @FindBy(css = "a[href='/login']")
  private WebElement loginLink;

  public UnsplashHomePage(WebDriver driver, SeleniumWrapper seleniumWrapper) {
    this.seleniumWrapper = seleniumWrapper;
    PageFactory.initElements(driver, this);
  }

  public void waitForPageLoad() {
    seleniumWrapper.waits.waitForPageLoad();
  }

  public void searchForImage(String query) {
    seleniumWrapper.elements.typeText(searchInput, query);
    seleniumWrapper.elements.clickElement(searchButton);
    seleniumWrapper.waits.waitForPageLoad();
  }

  public void navigateToExplorePage() {
    seleniumWrapper.elements.clickElement(exploreLink);
    seleniumWrapper.waits.waitForPageLoad();
  }

  public void navigateToLoginPage() {
    seleniumWrapper.elements.clickElement(loginLink);
    seleniumWrapper.waits.waitForPageLoad();
  }

  public boolean isSearchInputDisplayed() {
    return seleniumWrapper.elements.isElementDisplayed(searchInput);
  }

  public boolean isHomePageLoaded() {
    return seleniumWrapper.elements.isElementDisplayed(searchInput)
        && seleniumWrapper.elements.isElementDisplayed(searchButton)
        && seleniumWrapper.elements.isElementDisplayed(exploreLink)
        && seleniumWrapper.elements.isElementDisplayed(loginLink);
  }
}
