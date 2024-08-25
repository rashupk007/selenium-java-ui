package com.example.framework.utils.ui;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptActions {
  private final WebDriver driver;
  private final ExtentTest test;

  public JavaScriptActions(WebDriver driver, ExtentTest test) {
    this.driver = driver;
    this.test = test;
  }

  public Object executeJavaScript(String script, Object... args) {
    try {
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      Object result = executor.executeScript(script, args);
      test.log(Status.INFO, "Executed JavaScript: " + script);
      return result;
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to execute JavaScript: " + script);
      throw e;
    }
  }

  public void clickUsingJavaScript(By locator) {
    try {
      WebElement element = driver.findElement(locator);
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].click();", element);
      test.log(Status.INFO, "Clicked element using JavaScript: " + locator);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to click element using JavaScript: " + locator);
      throw e;
    }
  }

  public void scrollToElement(By locator) {
    try {
      WebElement element = driver.findElement(locator);
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].scrollIntoView(true);", element);
      test.log(Status.INFO, "Scrolled to element: " + locator);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to scroll to element: " + locator);
      throw e;
    }
  }

  public void scrollToTop() {
    try {
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("window.scrollTo(0, 0)");
      test.log(Status.INFO, "Scrolled to top of the page");
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to scroll to top of the page");
      throw e;
    }
  }

  public void scrollToBottom() {
    try {
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
      test.log(Status.INFO, "Scrolled to bottom of the page");
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to scroll to bottom of the page");
      throw e;
    }
  }

  public void dragAndDropUsingJavaScript(By sourceLocator, By targetLocator) {
    try {
      WebElement sourceElement = driver.findElement(sourceLocator);
      WebElement targetElement = driver.findElement(targetLocator);
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      String script =
          "function createEvent(typeOfEvent) {\n"
              + "    var event = document.createEvent(\"CustomEvent\");\n"
              + "    event.initCustomEvent(typeOfEvent, true, true, null);\n"
              + "    event.dataTransfer = {\n"
              + "        data: {},\n"
              + "        setData: function (key, value) {\n"
              + "            this.data[key] = value;\n"
              + "        },\n"
              + "        getData: function (key) {\n"
              + "            return this.data[key];\n"
              + "        }\n"
              + "    };\n"
              + "    return event;\n"
              + "}\n"
              + "\n"
              + "function dispatchEvent(element, event, transferData) {\n"
              + "    if (transferData !== undefined) {\n"
              + "        event.dataTransfer = transferData;\n"
              + "    }\n"
              + "    if (element.dispatchEvent) {\n"
              + "        element.dispatchEvent(event);\n"
              + "    } else if (element.fireEvent) {\n"
              + "        element.fireEvent(\"on\" + event.type, event);\n"
              + "    }\n"
              + "}\n"
              + "\n"
              + "function simulateHTML5DragAndDrop(element, target) {\n"
              + "    var dragStartEvent = createEvent('dragstart');\n"
              + "    dispatchEvent(element, dragStartEvent);\n"
              + "    var dropEvent = createEvent('drop');\n"
              + "    dispatchEvent(target, dropEvent, dragStartEvent.dataTransfer);\n"
              + "    var dragEndEvent = createEvent('dragend');\n"
              + "    dispatchEvent(element, dragEndEvent, dropEvent.dataTransfer);\n"
              + "}\n"
              + "\n"
              + "var source = arguments[0];\n"
              + "var target = arguments[1];\n"
              + "simulateHTML5DragAndDrop(source, target);";
      executor.executeScript(script, sourceElement, targetElement);
      test.log(
          Status.INFO,
          "Performed drag and drop using JavaScript from "
              + sourceLocator
              + " to "
              + targetLocator);
    } catch (Exception e) {
      test.log(
          Status.FAIL,
          "Failed to perform drag and drop using JavaScript from "
              + sourceLocator
              + " to "
              + targetLocator);
      throw e;
    }
  }
}
