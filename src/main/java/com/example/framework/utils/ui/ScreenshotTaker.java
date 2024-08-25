package com.example.framework.utils.ui;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotTaker {
  private final WebDriver driver;
  private final ExtentTest test;

  public ScreenshotTaker(WebDriver driver, ExtentTest test) {
    this.driver = driver;
    this.test = test;
  }

  public byte[] captureAsBytes() {
    try {
      byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      test.log(Status.INFO, "Screenshot taken");
      return screenshot;
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to take screenshot");
      throw e;
    }
  }

  public File captureAsFile() {
    try {
      File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      test.log(Status.INFO, "Screenshot taken as file");
      return screenshotFile;
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to take screenshot as file");
      throw e;
    }
  }

  public String captureAsBase64() {
    try {
      String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
      test.log(Status.INFO, "Screenshot taken as Base64");
      return base64Screenshot;
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to take screenshot as Base64");
      throw e;
    }
  }
}
