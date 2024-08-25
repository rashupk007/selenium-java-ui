package com.example.framework.utils.ui;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ScriptExecutor {
  private final WebDriver driver;
  private final ExtentTest test;

  public ScriptExecutor(WebDriver driver, ExtentTest test) {
    this.driver = driver;
    this.test = test;
  }

  public Object execute(String script, Object... args) {
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
}
