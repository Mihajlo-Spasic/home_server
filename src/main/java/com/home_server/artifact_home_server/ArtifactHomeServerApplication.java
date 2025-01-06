package com.home_server.artifact_home_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.openqa.selenium.WebDriver;
import com.home_server.artifact_home_server.listinghandling.Kupujem_Prodajem_instance;
import com.home_server.artifact_home_server.database.Database_instance;
import java.sql.*;

@SpringBootApplication
public class ArtifactHomeServerApplication {

  public static void main(String[] args) throws SQLException {
    SpringApplication.run(ArtifactHomeServerApplication.class, args);

    // Loading all the heavy code at the start
    Database_instance database = Database_instance.getInstance();
    Kupujem_Prodajem_instance browser = Kupujem_Prodajem_instance.getInstance();
    browser.testMethod();
  }

}
