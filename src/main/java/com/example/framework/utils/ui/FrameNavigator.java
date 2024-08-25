package com.example.framework.utils.ui;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameNavigator {
  private final WebDriver driver;
  private final ExtentTest test;

  public FrameNavigator(WebDriver driver, ExtentTest test) {
    this.driver = driver;
    this.test = test;
  }

  public void switchToFrame(By locator) {
    try {
      WebElement frameElement = driver.findElement(locator);
      driver.switchTo().frame(frameElement);
      test.log(Status.INFO, "Switched to frame: " + locator);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to switch to frame: " + locator);
      throw e;
    }
  }

  public void switchToDefaultContent() {
    try {
      driver.switchTo().defaultContent();
      test.log(Status.INFO, "Switched to default content");
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to switch to default content");
      throw e;
    }
  }

  public void switchToWindow(String windowHandle) {
    try {
      driver.switchTo().window(windowHandle);
      test.log(Status.INFO, "Switched to window: " + windowHandle);
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to switch to window: " + windowHandle);
      throw e;
    }
  }

  public Set<String> getWindowHandles() {
    Set<String> handles = driver.getWindowHandles();
    test.log(Status.INFO, "Retrieved window handles: " + handles);
    return handles;
  }
}
