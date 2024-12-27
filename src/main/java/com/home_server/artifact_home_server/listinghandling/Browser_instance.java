package com.home_server.artifact_home_server.listinghandling; 

import com.selenium_base.selenium_base_class;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.facebook_auth_selenium.Facebook_auth;
import com.home_server.artifact_home_server.database.Database_Instance;
import java.time.Duration;

// Browser Instance for KUPUJEMPRODAJEM listings
// Singleton pattern 
public class Browser_instance{ 

  public static WebDriver driver;
  public static FirefoxOptions options;
  
  public final static String browser = "firefox";
  public final static String driver_path = "/usr/bin/geckodriver";
  public final static String listing_url = "https://www.kupujemprodajem.com/moj-kp/moji-oglasi";
  public final static String add_listing_url = "https://www.kupujemprodajem.com/postavljanje-oglasa";
  public final static String connectability_class = "KpUserDisplayContainer_email__dzhQM";

  private static boolean set_gecko_location = false;
  private static Browser_instance instance;
  private static Database_Instance database;
  private Browser_instance(){   
    if (!set_gecko_location){
      System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
      set_gecko_location = true;
    }
    this.options = new FirefoxOptions();
    this.driver = new FirefoxDriver(options);
    this.database = Database_Instance.getInstance();
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
  public static boolean checkConnectability(WebDriver driver){
    try{
      WebElement button = driver.findElement(By.class(connectability_class));
      if (button.getAttribute("name").matches(System.getenv("facebook_email"))) 
        return true;
    }
    return false 
  } 
  
  //Complete Template for listsing automatization
  public void createListingTemplate(String list_name){
    driver.get(add_listing_url);

    WebDriverWait waitPageLoad = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement wait = waitPageLoad.until(ExpectedConditions.elementToBeClickable(By.class("Input_inputRow__YMC5T Input_label__JhaZY")));
    WebElement name_label = driver.findElement(By.class("Input_inputRow__YMC5T Input_label__JhaZY"));
    driver.sendKeys(list_name);
    WebElement searchButtonName = driver.findElement(By.class("Button_base__G3HTK Button_big__vkHxv ButtonPrimaryBlue_primaryBlue__OjE4b"));
    searchButtonName.click();
    Thread.sleep(5000);
    try {
      WebElement section = driver.findElement(By.className("AdSaveStepOne_suggestionList__NW_Yc"));
      List<WebElement> buttons = section.findElements(By.tagName("button"));
      for (WebElement button : buttons) {
        button.click();
        Thread.sleep(10000);
        break;
        }
      
    } catch (Exception e) {
        e.printStackTrace();
      }
    
  }


  //Set of methods for CRUD of listings managed by database
  public static void addListingFromDB(){}
  public static void updateListing(){}
  public static void removeListingFromDB(String name){}
  public static void removeListingFromDB(int id){}
}

