package com.home_server.artifact_home_server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.sql.*;

import com.home_server.artifact_home_server.database.*;
import java.util.List;

@Controller
@RequestMapping("kp")
public class KP_page{
  public Database_instance database; 
  public Connection connection;
  public Statement statement;
  public ResultSet resultSet;
  {
    try{
      database = Database_instance.getInstance();
      connection = database.getConnection();
      statement = connection.createStatement();
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

  // /kp/create
  @GetMapping("/create")
  public String create_listing(Model model){
    return "create";
  } 

  // /kp/create
  @PostMapping("/create")
  public String list_submit(
                             @RequestParam(value = "name", required = false) String name, 
                             @RequestParam(value = "category", required = false) String category,
                             @RequestParam(value = "subcategory", required = false) String subcategory,
                             @RequestParam(value = "description", required = false) String description,
                             @RequestParam(value = "images", required = false) List<String> images ,
                             Model model) throws SQLException{
    //String query = "INSERT INTO " + System.getenv("DB_TABLE_KP") + "(name, category, subcategory, description) VALUES (" + name + "," + category + "," + subcategory + "," + description + ");";
    //resultSet = statement.executeQuery(query);
    System.out.println(name);
    System.out.println("\nHERE\n");
    return "result";
  }
  
 
}
