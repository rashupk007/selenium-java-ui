package com.example.framework.utils.ui;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

public class NavigationMaster {
  private final WebDriver driver;
  private final ExtentTest test;

  public NavigationMaster(WebDriver driver, ExtentTest test) {
    this.driver = driver;
    this.test = test;
  }

  public void goToUrl(String url) {
    try {
      driver.get(url);
      test.log(Status.INFO, "Navigated to URL: " + url);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to navigate to URL: " + url);
      throw e;
    }
  }

  public void scrollToTop() {
    try {
      ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
      test.log(Status.INFO, "Scrolled to top of the page");
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to scroll to top of the page");
      throw e;
    }
  }

  public void scrollToBottom() {
    try {
      ((org.openqa.selenium.JavascriptExecutor) driver)
          .executeScript("window.scrollTo(0, document.body.scrollHeight)");
      test.log(Status.INFO, "Scrolled to bottom of the page");
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to scroll to bottom of the page");
      throw e;
    }
  }

  public String getCurrentUrl() {
    try {
      String currentUrl = driver.getCurrentUrl();
      test.log(Status.INFO, "Current URL: " + currentUrl);
      return currentUrl;
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to retrieve current URL");
      throw e;
    }
  }
}
