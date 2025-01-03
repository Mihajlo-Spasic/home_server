package com.home_server.artifact_home_server.formsubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.home_server.artifact_home_server.database.*;
import java.sql.*;
import java.util.List;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

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

  //Event triggers on form submission passing the listing object with form data in the "new_listing" variable
  @PostMapping("/create")
  public String list_submit( @RequestParam("images") MultipartFile[] image_file, @ModelAttribute Listing new_listing, Model model) throws SQLException, IOException{ 
    model.addAttribute("listing", new_listing);
    System.out.println(image_file);

    database = Database_instance.getInstance();
    connection = database.getConnection();
    statement = connection.createStatement();
    create_query_for_list(new_listing.name,new_listing.category,new_listing.subcategory,new_listing.description, new_listing.price, new_listing.currency, new_listing.images, image_file);

    return "redirect:/kp";
  }
  public void make_directories_for_entries(int item_id){ 
    File dir = new File("images/" + item_id);
    if (!dir.exists()){
      dir.mkdir();
    }
  } 

  public void place_image(int item_id, MultipartFile image_file) throws IOException{
  	String uploadDir = "images/" + String.valueOf(item_id);
	
	String file_name = image_file.getOriginalFilename();
	Path file_path = Paths.get(uploadDir, file_name);
	image_file.transferTo(file_path.toFile());
  }
  //public void populate_directory(int dir_number,  
  //In /create url creates new database entry for listing and image of the same listing 
  private void create_query_for_list(String name, String category, String subcategory, String description, int price, String currency, List<String> images, MultipartFile[] image_file_list) throws SQLException, IOException {    // make correction for category & subcategory if they are wrong
     String query = "INSERT INTO " + System.getenv("DB_TABLE_KP") + " (name, category, subcategory, description, price, currency) VALUES (?, ?, ?, ?, ?, ?)";
     String picture_query = "INSERT INTO " + System.getenv("DB_IMAGES_KP") + " (item_id, picture_url) VALUES (?, ?)";
     
     PreparedStatement pictureStmt;
    
     try{ 
      PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      pictureStmt = connection.prepareStatement(picture_query); 
      preparedStatement.setString(1, name);
      preparedStatement.setString(2, category);
      preparedStatement.setString(3, subcategory);
      preparedStatement.setString(4, description);
      preparedStatement.setString(5, Integer.toString(price));
      preparedStatement.setString(6, currency);
    
      preparedStatement.executeUpdate();
      
      ResultSet generatedId = preparedStatement.getGeneratedKeys();
      int item_id = 0;
      if (generatedId.next()){
        item_id = generatedId.getInt(1);
      }
      else{
        System.err.println("item_id call failed [createListing.java:75]");
        return;
      }

      make_directories_for_entries(item_id);
      //Makes an entry for each image passed in /create form with the id of the latest listing entry/object 
      for(String image: images){
	//populate_directory(item_id, image);
        pictureStmt.setInt(1, item_id);
        // example images/10/Dogimg4.jpg
        pictureStmt.setString(2, "images/" + item_id + "/" + image);  
        pictureStmt.executeUpdate(); 
      }
      for (MultipartFile image_f : image_file_list){
	      place_image(item_id, image_f);
      }
     } catch (SQLException e) {
    e.printStackTrace();
    }
  }
}
