package com.home_server.artifact_home_server.formsubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import com.home_server.artifact_home_server.database.*;
import java.sql.*;

@Controller
public class createListing{

  public Database_instance database; 
  public Connection connection;
  public Statement statement;
  public ResultSet resultSet;
  
  @GetMapping("/create")
  public String create_listing(Model model) throws SQLException{
    model.addAttribute("listing",new Listing());
    
    return "/create";
  } 

  @PostMapping("/create")
  public String list_submit( @ModelAttribute Listing new_listing, Model model) throws SQLException{ 
    model.addAttribute("listing", new_listing);

    database = Database_instance.getInstance();
    connection = database.getConnection();
    statement = connection.createStatement();
    create_query_for_list(new_listing.name,new_listing.category,new_listing.subcategory,new_listing.description);

    return "redirect:/kp";
  }
  
  private void create_query_for_list(String name, String category, String subcategory, String description) throws SQLException{
    
    // make correction for category & subcategory if they are wrong
 String query = "INSERT INTO " + System.getenv("DB_TABLE_KP") + 
        " (name, category, subcategory, description) VALUES ('" + name + "', '" + category + "', '" + subcategory + "', '" + description + "');";
    int result = statement.executeUpdate(query);
     
  }
}
