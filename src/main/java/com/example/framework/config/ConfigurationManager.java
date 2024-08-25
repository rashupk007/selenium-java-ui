package com.example.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
  private static final Properties properties = new Properties();

  static {
    try (InputStream input =
        ConfigurationManager.class.getClassLoader().getResourceAsStream("config.properties")) {
      if (input != null) {
        properties.load(input);
      } else {
        System.err.println("Sorry, unable to find config.properties");
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static String getProperty(String key) {
    return properties.getProperty(key);
  }

  public static Browser getBrowser() {
    String browserName = getProperty("browser");
    if (browserName != null) {
      return Browser.valueOf(browserName.toUpperCase());
    } else {
      throw new IllegalArgumentException("Browser name not specified in config.properties");
    }
  }
}
