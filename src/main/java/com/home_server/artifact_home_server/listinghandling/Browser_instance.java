package com.home_server.artifact_home_server.listinghandling; 

import com.selenium_base.selenium_base_class;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.facebook_auth_selenium.Facebook_auth;


// Browser Instance for KUPUJEMPRODAJEM listings
// Singleton pattern 
public class Browser_instance{ 

  public static WebDriver driver;
  public static FirefoxOptions options;
  
  public final static String browser = "firefox";
  public final static String driver_path = "/usr/bin/geckodriver";
  public final static String listing_url ="https://www.kupujemprodajem.com/moj-kp/moji-oglasi";


  private static boolean set_gecko_location = false;
  private static Browser_instance instance;

  private Browser_instance(){   
    if (!set_gecko_location){
      System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
      set_gecko_location = true;
    }
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
  
  //Method checks if user is still logged to the platform kupujemprodajem
  public static void checkConnectability(){} 
  
  //Set of methods for CRUD of listings managed by database
  public static void updateListing(){}
  public static void addListingFromDB(){}
  public static void removeListingFromDB(String name){}
  public static void removeListingFromDB(int id){}
}

