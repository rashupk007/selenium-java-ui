package com.example.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverFactory implements DriverFactory {
  @Override
  public WebDriver createDriver() {
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--start-maximized");
    return new FirefoxDriver(options);
  }
}
