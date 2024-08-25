package com.example.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverFactory implements DriverFactory {
  @Override
  public WebDriver createDriver() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    return new ChromeDriver(options);
  }
}
