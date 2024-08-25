package com.example.pages;

import com.example.framework.utils.ui.SeleniumWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UnsplashLoginPage {

  private SeleniumWrapper seleniumWrapper;

  @FindBy(css = "input[name='user[email]']")
  private WebElement emailInput;

  @FindBy(css = "input[name='user[password]']")
  private WebElement passwordInput;

  @FindBy(css = "input[type='submit']")
  private WebElement loginButton;

  @FindBy(css = "div[data-test='form-error']")
  private WebElement errorMessage;

  public UnsplashLoginPage(WebDriver driver, SeleniumWrapper seleniumWrapper) {
    this.seleniumWrapper = seleniumWrapper;
    PageFactory.initElements(driver, this);
  }

  public void login(String email, String password) {
    seleniumWrapper.elements.typeText(emailInput, email);
    seleniumWrapper.elements.typeText(passwordInput, password);
    seleniumWrapper.elements.clickElement(loginButton);
    seleniumWrapper.waits.waitForPageLoad();
  }

  public boolean isErrorMessageDisplayed() {
    return seleniumWrapper.elements.isElementDisplayed(errorMessage);
  }

  public String getErrorMessage() {
    return seleniumWrapper.elements.retrieveText(errorMessage);
  }

  public boolean isLoginButtonEnabled() {
    return seleniumWrapper.elements.isEnabled(loginButton);
  }
}
