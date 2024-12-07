package com.home_server.artifact_home_server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.home_server.artifact_home_server.database.*;
import java.util.List;

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
  public String home_page(Model model) throws SQLException{
    Listing listing_database = new Listing();
    List<Item> listings = listing_database.getAllItems();

    model.addAttribute("listings",listings );
    return "kp";
  }

  @GetMapping("/create")
  public String create_listing(){
    return "create";
  }
  
  @PostMapping
  public String load_all_kp_listings() {
        System.out.println("load all listings called!");
        
        return "/kp"; 
    }
 
}
