package com.example.framework.utils.ui;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import java.time.Duration;
import java.util.function.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitMaster {
  private final WebDriver driver;
  private final ExtentTest test;
  private static final int DEFAULT_TIMEOUT = 10;

  public WaitMaster(WebDriver driver, ExtentTest test) {
    this.driver = driver;
    this.test = test;
  }

  public void waitForVisibility(By locator, int timeoutInSeconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
      wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      test.log(Status.INFO, "Element visible after wait: " + locator);
    } catch (Exception e) {
      test.log(
          Status.FAIL, "Element not visible after " + timeoutInSeconds + " seconds: " + locator);
      throw e;
    }
  }

  public void waitForClickability(By locator, int timeoutInSeconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
      wait.until(ExpectedConditions.elementToBeClickable(locator));
      test.log(Status.INFO, "Element clickable after wait: " + locator);
    } catch (Exception e) {
      test.log(
          Status.FAIL, "Element not clickable after " + timeoutInSeconds + " seconds: " + locator);
      throw e;
    }
  }

  public void waitForVisibility(By locator) {
    waitForVisibility(locator, DEFAULT_TIMEOUT);
  }

  public void waitForClickability(By locator) {
    waitForClickability(locator, DEFAULT_TIMEOUT);
  }

  public void waitForPageLoad(int timeoutInSeconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
      wait.until(
          (ExpectedCondition<Boolean>)
              wd ->
                  ((JavascriptExecutor) wd)
                      .executeScript("return document.readyState")
                      .equals("complete"));
      test.log(Status.INFO, "Page loaded successfully");
    } catch (Exception e) {
      test.log(Status.FAIL, "Page did not load within " + timeoutInSeconds + " seconds");
      throw e;
    }
  }

  public void waitForPageLoad() {
    waitForPageLoad(DEFAULT_TIMEOUT);
  }

  public void waitForElementToBePresent(By locator, int timeoutInSeconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
      wait.until(ExpectedConditions.presenceOfElementLocated(locator));
      test.log(Status.INFO, "Element present after wait: " + locator);
    } catch (Exception e) {
      test.log(
          Status.FAIL, "Element not present after " + timeoutInSeconds + " seconds: " + locator);
      throw e;
    }
  }

  public void waitForElementToBePresent(By locator) {
    waitForElementToBePresent(locator, DEFAULT_TIMEOUT);
  }

  public void waitForElementToBeInvisible(By locator, int timeoutInSeconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
      wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
      test.log(Status.INFO, "Element invisible after wait: " + locator);
    } catch (Exception e) {
      test.log(
          Status.FAIL, "Element still visible after " + timeoutInSeconds + " seconds: " + locator);
      throw e;
    }
  }

  public void waitForElementToBeInvisible(By locator) {
    waitForElementToBeInvisible(locator, DEFAULT_TIMEOUT);
  }

  public void waitForTextToBePresentInElement(By locator, String text, int timeoutInSeconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
      wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
      test.log(Status.INFO, "Text present in element after wait: " + locator + ", Text: " + text);
    } catch (Exception e) {
      test.log(
          Status.FAIL,
          "Text not present in element after "
              + timeoutInSeconds
              + " seconds: "
              + locator
              + ", Text: "
              + text);
      throw e;
    }
  }

  public void waitForTextToBePresentInElement(By locator, String text) {
    waitForTextToBePresentInElement(locator, text, DEFAULT_TIMEOUT);
  }

  public void waitForAttributeToContain(
      By locator, String attribute, String value, int timeoutInSeconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
      wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
      test.log(
          Status.INFO,
          "Attribute contains value after wait: "
              + locator
              + ", Attribute: "
              + attribute
              + ", Value: "
              + value);
    } catch (Exception e) {
      test.log(
          Status.FAIL,
          "Attribute does not contain value after "
              + timeoutInSeconds
              + " seconds: "
              + locator
              + ", Attribute: "
              + attribute
              + ", Value: "
              + value);
      throw e;
    }
  }

  public void waitForAttributeToContain(By locator, String attribute, String value) {
    waitForAttributeToContain(locator, attribute, value, DEFAULT_TIMEOUT);
  }

  public void waitForNumberOfElementsToBe(By locator, int expectedCount, int timeoutInSeconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
      wait.until(ExpectedConditions.numberOfElementsToBe(locator, expectedCount));
      test.log(
          Status.INFO,
          "Number of elements matches expected count after wait: "
              + locator
              + ", Expected count: "
              + expectedCount);
    } catch (Exception e) {
      test.log(
          Status.FAIL,
          "Number of elements does not match expected count after "
              + timeoutInSeconds
              + " seconds: "
              + locator
              + ", Expected count: "
              + expectedCount);
      throw e;
    }
  }

  public void waitForNumberOfElementsToBe(By locator, int expectedCount) {
    waitForNumberOfElementsToBe(locator, expectedCount, DEFAULT_TIMEOUT);
  }

  public void waitForUrlToContain(String urlFragment, int timeoutInSeconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
      wait.until(ExpectedConditions.urlContains(urlFragment));
      test.log(Status.INFO, "URL contains fragment after wait: " + urlFragment);
    } catch (Exception e) {
      test.log(
          Status.FAIL,
          "URL does not contain fragment after " + timeoutInSeconds + " seconds: " + urlFragment);
      throw e;
    }
  }

  public void waitForUrlToContain(String urlFragment) {
    waitForUrlToContain(urlFragment, DEFAULT_TIMEOUT);
  }

  public void waitForJsCondition(String jsCondition, int timeoutInSeconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
      wait.until(
          (ExpectedCondition<Boolean>)
              wd ->
                  (Boolean)
                      ((JavascriptExecutor) wd).executeScript("return (" + jsCondition + ")"));
      test.log(Status.INFO, "JavaScript condition met after wait: " + jsCondition);
    } catch (Exception e) {
      test.log(
          Status.FAIL,
          "JavaScript condition not met after " + timeoutInSeconds + " seconds: " + jsCondition);
      throw e;
    }
  }

  public void waitForJsCondition(String jsCondition) {
    waitForJsCondition(jsCondition, DEFAULT_TIMEOUT);
  }

  public <T> T waitFor(Function<WebDriver, T> condition, int timeoutInSeconds) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
      T result = wait.until(condition);
      test.log(Status.INFO, "Custom condition met after wait");
      return result;
    } catch (Exception e) {
      test.log(Status.FAIL, "Custom condition not met after " + timeoutInSeconds + " seconds");
      throw e;
    }
  }

  public <T> T waitFor(Function<WebDriver, T> condition) {
    return waitFor(condition, DEFAULT_TIMEOUT);
  }
}
