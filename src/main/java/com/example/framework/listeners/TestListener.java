package com.example.framework.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.example.framework.reporting.ExtentReportManager;
import com.example.framework.utils.api.RestAssuredWrapper;
import com.example.framework.utils.ui.SeleniumWrapper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

  private ThreadLocal<SeleniumWrapper> seleniumWrapper = new ThreadLocal<>();
  private ThreadLocal<RestAssuredWrapper> restAssuredWrapper = new ThreadLocal<>();

  @Override
  public void onStart(ITestContext context) {
    ExtentReportManager.getExtentReports();
  }

  @Override
  public void onFinish(ITestContext context) {
    ExtentReportManager.flushReports();
    seleniumWrapper.remove();
    restAssuredWrapper.remove();
  }

  @Override
  public void onTestStart(ITestResult result) {
    WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
    if (driver != null) {
      seleniumWrapper.set(new SeleniumWrapper(driver));
    }

    RestAssuredWrapper apiWrapper =
        (RestAssuredWrapper) result.getTestContext().getAttribute("RestAssuredWrapper");
    if (apiWrapper != null) {
      restAssuredWrapper.set(apiWrapper);
    }

    ExtentReportManager.startTest(
        result.getMethod().getMethodName(), result.getTestClass().getName());
    ExtentReportManager.getTest().info("Test Started: " + result.getMethod().getMethodName());
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    ExtentReportManager.logTestResult(result);
    ExtentReportManager.getTest().info("Test Passed: " + result.getMethod().getMethodName());
  }

  @Override
  public void onTestFailure(ITestResult result) {
    try {
      ExtentReportManager.logTestResult(result);
      if (seleniumWrapper.get() != null && restAssuredWrapper.get() == null) {
        captureScreenshotOnFailure(result);
      }
      ExtentReportManager.getTest().fail(result.getThrowable());
    } catch (Exception e) {
      ExtentReportManager.getTest().fail("Error while handling test failure: " + e.getMessage());
    }
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    ExtentReportManager.logTestResult(result);
    ExtentReportManager.getTest().info("Test Skipped: " + result.getMethod().getMethodName());
  }

  private void captureScreenshotOnFailure(ITestResult result) {
    try {
      String base64Screenshot = seleniumWrapper.get().screenshots.captureAsBase64();
      ExtentReportManager.getTest()
          .fail(
              "Test failed: " + result.getMethod().getMethodName(),
              MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    } catch (Exception e) {
      ExtentReportManager.getTest()
          .fail("Failed to capture screenshot on test failure: " + e.getMessage());
    }
  }
}
