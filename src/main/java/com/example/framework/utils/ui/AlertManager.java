package com.example.framework.utils.ui;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertManager {
  private final WebDriver driver;
  private final ExtentTest test;
  private static final int DEFAULT_TIMEOUT = 10;

  public AlertManager(WebDriver driver, ExtentTest test) {
    this.driver = driver;
    this.test = test;
  }

  public void handleAlert(String action) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
      Alert alert = wait.until(ExpectedConditions.alertIsPresent());

      String alertText = alert.getText();
      test.log(Status.INFO, "Alert text: " + alertText);

      switch (action.toLowerCase()) {
        case "accept":
          alert.accept();
          test.log(Status.INFO, "Alert accepted");
          break;
        case "dismiss":
          alert.dismiss();
          test.log(Status.INFO, "Alert dismissed");
          break;
        case "gettext":
          test.log(Status.INFO, "Retrieved alert text");
          break;
        default:
          throw new IllegalArgumentException("Invalid alert action: " + action);
      }
    } catch (Exception e) {
      test.log(Status.FAIL, "Failed to handle alert: " + e.getMessage());
      throw e;
    }
  }
}
