package com.home_server.artifact_home_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.openqa.selenium.WebDriver;
import com.home_server.artifact_home_server.listinghandling.Kupujem_Prodajem_instance;
import com.facebook_auth_selenium.Facebook_auth;
import com.home_server.artifact_home_server.database.Database_instance;
import java.sql.*;
import com.facebook_auth_selenium.Facebook_auth;

@SpringBootApplication
public class ArtifactHomeServerApplication {
  public final static String listing_url = "https://www.kupujemprodajem.com/moj-kp/moji-oglasi";

  public static void main(String[] args) throws SQLException {
    SpringApplication.run(ArtifactHomeServerApplication.class, args);

    // Loading all the heavy code at the start
    Database_instance database = Database_instance.getInstance();
    Kupujem_Prodajem_instance browser = Kupujem_Prodajem_instance.getInstance();
    // browser.testMethod();

    // test remove later
    WebDriver driver = browser.getDriver();
    Facebook_auth fb = new Facebook_auth(driver, listing_url);
    fb.load_credentials_env();
    fb.execute_auth();

  }

}
