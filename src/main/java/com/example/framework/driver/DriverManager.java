package com.example.framework.driver;

import com.example.framework.config.Browser;
import com.example.framework.config.ConfigurationManager;
import org.openqa.selenium.WebDriver;

public class DriverManager {
  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

  public static WebDriver getDriver() {
    if (driver.get() == null) {
      driver.set(createDriver());
    }
    return driver.get();
  }

  public static void quitDriver() {
    if (driver.get() != null) {
      driver.get().quit();
      driver.remove();
    }
  }

  private static WebDriver createDriver() {
    Browser browser = ConfigurationManager.getBrowser();
    DriverFactory factory;

    switch (browser) {
      case CHROME:
        factory = new ChromeDriverFactory();
        break;
      case FIREFOX:
        factory = new FirefoxDriverFactory();
        break;
      default:
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }

    return factory.createDriver();
  }
}
