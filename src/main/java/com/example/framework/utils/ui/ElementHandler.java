package com.example.framework.utils.ui;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementHandler {
  private final WebDriver driver;
  private final ExtentTest test;
  private final Actions actions;

  public ElementHandler(WebDriver driver, ExtentTest test) {
    this.driver = driver;
    this.test = test;
    this.actions = new Actions(driver);
  }

  public void clickElement(By locator) {
    try {
      driver.findElement(locator).click();
      test.log(Status.INFO, "Clicked element: " + locator);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to click element: " + locator);
      throw e;
    }
  }

  public void clickElement(WebElement element) {
    try {
      element.click();
      test.log(Status.INFO, "Clicked element: " + element);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to click element: " + element);
      throw e;
    }
  }

  public void typeText(By locator, String text) {
    try {
      driver.findElement(locator).sendKeys(text);
      test.log(Status.INFO, "Entered text '" + text + "' into element: " + locator);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to enter text '" + text + "' into element: " + locator);
      throw e;
    }
  }

  public void typeText(WebElement element, String text) {
    try {
      element.sendKeys(text);
      test.log(Status.INFO, "Entered text '" + text + "' into element: " + element);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to enter text '" + text + "' into element: " + element);
      throw e;
    }
  }

  public String retrieveText(By locator) {
    try {
      String text = driver.findElement(locator).getText();
      test.log(Status.INFO, "Got text '" + text + "' from element: " + locator);
      return text;
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to get text from element: " + locator);
      throw e;
    }
  }

  public String retrieveText(WebElement element) {
    try {
      String text = element.getText();
      test.log(Status.INFO, "Got text '" + text + "' from element: " + element);
      return text;
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to get text from element: " + element);
      throw e;
    }
  }

  public WebElement retrieveElement(By locator) {
    try {
      WebElement element = driver.findElement(locator);
      test.log(Status.INFO, "Retrieved element: " + locator);
      return element;
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to retrieve element: " + locator);
      throw e;
    }
  }

  public void hoverOverElement(By locator) {
    try {
      WebElement element = retrieveElement(locator);
      actions.moveToElement(element).perform();
      test.log(Status.INFO, "Hovered over element: " + locator);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to hover over element: " + locator);
      throw e;
    }
  }

  public void rightClickElement(By locator) {
    try {
      WebElement element = retrieveElement(locator);
      actions.contextClick(element).perform();
      test.log(Status.INFO, "Right-clicked on element: " + locator);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to right-click on element: " + locator);
      throw e;
    }
  }

  public void doubleClickElement(By locator) {
    try {
      WebElement element = retrieveElement(locator);
      actions.doubleClick(element).perform();
      test.log(Status.INFO, "Double-clicked on element: " + locator);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to double-click on element: " + locator);
      throw e;
    }
  }

  public void dragAndDrop(By sourceLocator, By targetLocator) {
    try {
      WebElement sourceElement = retrieveElement(sourceLocator);
      WebElement targetElement = retrieveElement(targetLocator);
      actions.dragAndDrop(sourceElement, targetElement).perform();
      test.log(Status.INFO, "Dragged element from " + sourceLocator + " to " + targetLocator);
    } catch (Exception e) {
      test.log(
          Status.FAIL,
          "Failed to drag and drop element from " + sourceLocator + " to " + targetLocator);
      throw e;
    }
  }

  public void dragAndDropByOffset(By locator, int xOffset, int yOffset) {
    try {
      WebElement element = retrieveElement(locator);
      actions.dragAndDropBy(element, xOffset, yOffset).perform();
      test.log(
          Status.INFO,
          "Dragged element: " + locator + " by offset x: " + xOffset + ", y: " + yOffset);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to drag and drop element by offset for locator: " + locator);
      throw e;
    }
  }

  public boolean isElementDisplayed(WebElement element) {
    try {
      boolean isDisplayed = element.isDisplayed();
      test.log(Status.INFO, "Element display status: " + isDisplayed);
      return isDisplayed;
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to check if element is displayed: " + element);
      return false;
    }
  }

  public boolean isEnabled(WebElement element) {
    try {
      boolean isEnabled = element.isEnabled();
      test.log(Status.INFO, "Element enabled status: " + isEnabled);
      return isEnabled;
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to check if element is enabled: " + element);
      return false;
    }
  }
}
