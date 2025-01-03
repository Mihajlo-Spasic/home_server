package com.home_server.artifact_home_server.listinghandling; 

import com.selenium_base.selenium_base_class;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import com.facebook_auth_selenium.Facebook_auth;
import com.home_server.artifact_home_server.database.Database_instance;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
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
  private static Database_instance database;

  private Browser_instance() throws SQLException{   
    if (!set_gecko_location){
      System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
      set_gecko_location = true;
    }
    this.options = new FirefoxOptions();
    this.driver = new FirefoxDriver(options);
    this.database = Database_instance.getInstance();
  }
  
  public WebDriver getDriver(){
    return driver;
  }

  public static Browser_instance getInstance() throws SQLException{
    if (instance == null){
      instance = new Browser_instance();
    } 
    return instance;
  }

  public static void onError(){
    driver.close();
    instance = null;
  }
  
  //Method checks if user is still logged to the platform kupujemprodajem
  public static boolean checkConnectability(WebDriver driver){
    try{
      WebElement button = driver.findElement(By.className(connectability_class));
      if (button.getAttribute("name").matches(System.getenv("facebook_email"))){ 
        return true;
      }
      
    }catch(Exception e){
      e.printStackTrace();
     }
    return false; 
  } 
  
  //Complete Template for listsing automatization
  public static void createListingTemplate(String list_name, List<String> images, int price, String description, String currency){
    

    //test remove later
    Facebook_auth fb = new Facebook_auth(driver, listing_url);
    fb.load_credentials_env();
    fb.execute_auth();

    driver.get(add_listing_url);

    WebDriverWait waitPageLoad = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement wait = waitPageLoad.until(ExpectedConditions.elementToBeClickable(By.id("groupSuggestInputText")));
    WebElement name_label = driver.findElement(By.id("groupSuggestInputText"));
    name_label.sendKeys(list_name);
    WebElement searchButtonName = driver.findElement(By.className("ButtonPrimaryBlue_primaryBlue__OjE4b"));
    searchButtonName.click();
    try {
      Thread.sleep(5000);
      WebElement section = driver.findElement(By.className("AdSaveStepOne_suggestionList__NW_Yc"));
      List<WebElement> buttons = section.findElements(By.className("AdSaveStepOne_suggestionItem__zbMRN"));
      for (WebElement button : buttons) {
        button.click();
        Thread.sleep(10000);
        break;
        }
      
    } catch (Exception e) {
        e.printStackTrace();
      }

      //For the creators of selenium I can not select pictures to attach holy  
      WebElement imageAttachmentButton; 
      for (String image : images){
        imageAttachmentButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div/form/div[2]/div/div[2]/div/section/section[1]/div[2]/section[2]/div/div/div/div/input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", imageAttachmentButton);
        imageAttachmentButton.sendKeys(image);
      }   

      //WebElement price_input = driver.findElement


  }
  public static void testMethod(){
    List<String> images = new ArrayList<String>();
    images.add("/home/spale/testimg/path1.jpg");
    images.add("/home/spale/testimg/path2.jpg");

    //createListingTemplate("televizor",images);
  }

  //Set of methods for CRUD of listings managed by database
  public static void addListingFromDB(){}
  public static void updateListing(){}
  public static void removeListingFromDB(String name){}
  public static void removeListingFromDB(int id){}
}

