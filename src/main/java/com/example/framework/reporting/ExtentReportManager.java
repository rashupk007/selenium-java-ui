package com.example.framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import org.testng.ITestResult;

public class ExtentReportManager {
  private static ExtentReports extent;
  private static final ConcurrentHashMap<Long, ExtentTest> testMap = new ConcurrentHashMap<>();

  public static synchronized ExtentReports getExtentReports() {
    if (extent == null) {
      extent = new ExtentReports();
      File reportDir = new File("test-output");
      if (!reportDir.exists()) {
        reportDir.mkdir();
      }
      ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/extent-report.html");
      sparkReporter.config().setTheme(Theme.STANDARD);
      sparkReporter.config().setDocumentTitle("Automation Test Report");
      sparkReporter.config().setReportName("Selenium Test Results");
      extent.attachReporter(sparkReporter);
    }
    return extent;
  }

  public static ExtentTest getTest() {
    return testMap.get(Thread.currentThread().getId());
  }

  public static ExtentTest startTest(String testName, String desc) {
    ExtentTest test = getExtentReports().createTest(testName, desc);
    testMap.put(Thread.currentThread().getId(), test);
    return test;
  }

  public static void endTest() {
    testMap.remove(Thread.currentThread().getId());
  }

  public static void logTestResult(ITestResult result) {
    ExtentTest test = getTest();
    if (test != null) {
      if (result.getStatus() == ITestResult.FAILURE) {
        test.fail("Test failed: " + result.getThrowable());
      } else if (result.getStatus() == ITestResult.SKIP) {
        test.skip("Test skipped: " + result.getThrowable());
      } else {
        test.pass("Test passed");
      }
    }
  }

  public static synchronized void flushReports() {
    if (extent != null) {
      extent.flush();
    }
  }
}
