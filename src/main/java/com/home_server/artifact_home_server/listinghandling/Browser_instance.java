package com.home_server.artifact_home_server.listinghandling; 

import com.selenium_base.selenium_base_class;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.facebook_auth_selenium.Facebook_auth;

public class Browser_instance{ 

  public static WebDriver driver;
  public static FirefoxOptions options;
  
  public static String browser = "firefox";
  public static String driver_path = "/usr/bin/geckodriver";

  private static Browser_instance instance;

  private Browser_instance(){    
    this.options = new FirefoxOptions();
    this.driver = new FirefoxDriver(options);
  
  }

  public static WebDriver getInstance(){
    if (instance == null){
      instance = new Browser_instance();
    } 
    return driver;
  }

  public static void onError(){
    driver.close();
    instance = null;
  }
    
  
}
