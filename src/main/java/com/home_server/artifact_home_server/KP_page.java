package com.home_server.artifact_home_server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.home_server.artifact_home_server.database.Database_instance;
import com.home_server.artifact_home_server.database.Listings;

@Controller
@RequestMapping("kp")
public class KP_page{
  public Database_instance database; 
  public Connection connection;
  
  {
    try{
      database = Database_instance.getInstance();
      connection = database.getConnection();
    } catch(SQLException exception){
      exception.printStackTrace();
    }
  }
  

  @GetMapping
  public String home_page(){
    return "kp";
  }

  @GetMapping("/create")
  public String create_listing(){
    return "create";
  }
  
  @PostMapping
  public String load_all_kp_listings() {
        System.out.println("load all listings called!");
        
        return home_page(); 
    }
 
}
