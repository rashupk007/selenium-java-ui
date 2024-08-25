package com.example.framework.utils.ui;

import com.aventstack.extentreports.ExtentTest;
import com.example.framework.reporting.ExtentReportManager;
import org.openqa.selenium.WebDriver;

public class SeleniumWrapper {
  public final NavigationMaster navigation;
  public final ElementHandler elements;
  public final ScriptExecutor scripts;
  public final FrameNavigator frames;
  public final ScreenshotTaker screenshots;
  public final WaitMaster waits;
  public final AlertManager alerts;
  private final ExtentTest test;

  public SeleniumWrapper(WebDriver driver) {
    this.test = ExtentReportManager.getTest();
    this.navigation = new NavigationMaster(driver, test);
    this.elements = new ElementHandler(driver, test);
    this.scripts = new ScriptExecutor(driver, test);
    this.frames = new FrameNavigator(driver, test);
    this.screenshots = new ScreenshotTaker(driver, test);
    this.waits = new WaitMaster(driver, test);
    this.alerts = new AlertManager(driver, test);
  }
}
