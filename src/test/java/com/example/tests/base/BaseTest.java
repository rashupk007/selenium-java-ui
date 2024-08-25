package com.example.tests.base;

import com.example.framework.driver.DriverManager;
import com.example.framework.listeners.TestListener;
import com.example.framework.utils.ui.SeleniumWrapper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(TestListener.class)
public class BaseTest {
  protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
  protected ThreadLocal<SeleniumWrapper> seleniumWrapper = new ThreadLocal<>();
  protected ThreadLocal<String> baseUrl = new ThreadLocal<>();

  @Parameters({"baseUrl"})
  @BeforeMethod
  public void setUp(ITestContext context, String baseUrl) {
    driver.set(DriverManager.getDriver());
    seleniumWrapper.set(new SeleniumWrapper(driver.get()));
    this.baseUrl.set(baseUrl);
    context.setAttribute("WebDriver", driver.get());
    context.setAttribute("BaseUrl", baseUrl);
  }

  @AfterMethod
  public void tearDown(ITestContext context) {
    DriverManager.quitDriver();
    driver.remove();
    seleniumWrapper.remove();
    baseUrl.remove();
    context.removeAttribute("WebDriver");
    context.removeAttribute("BaseUrl");
  }

  protected WebDriver getDriver() {
    return driver.get();
  }

  protected SeleniumWrapper getSeleniumWrapper() {
    return seleniumWrapper.get();
  }

  protected String getBaseUrl() {
    return baseUrl.get();
  }
}
