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
import java.util.ArrayList;
import java.util.Arrays;

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
  @PostMapping(value = "/create", consumes = "multipart/form-data") 
  public String list_submit( 
		  //@RequestParam("imageFiles") MultipartFile[] image_file, 
		  @ModelAttribute Listing new_listing, 
		  Model model)	 throws SQLException, IOException{ 


    model.addAttribute("listing", new_listing);

    
    database = Database_instance.getInstance();
    connection = database.getConnection();
    statement = connection.createStatement();

    create_query_for_list(new_listing);

    return "redirect:/kp";
  }
  
  //Makes an entry for each image passed in /create form with the id of the latest listing entry/object 
  public File make_directories_for_entries(int item_id) {
    File dir = new File("images/" + item_id);
    if (!dir.exists()) {
        boolean created = dir.mkdirs();  // Use mkdirs to create all necessary parent directories
        if (created) {
            System.out.println("Directory created: " + dir.getAbsolutePath());
        } else {
            System.out.println("Failed to create directory: " + dir.getAbsolutePath());
        }
    }
    return dir;
}

// Method to place the image into the corresponding directory
public void place_image(int item_id, MultipartFile image_file) throws IOException {
    File file = make_directories_for_entries(item_id);  // Ensure directory exists

    // Define the full path, including the file name
    String uploadDir = "images/" + item_id + "/";
    Path filePath = Paths.get(uploadDir, image_file.getOriginalFilename()); // Append filename

    // Log the file path for debugging
    System.out.println("Saving image to: " + filePath.toAbsolutePath());

    // Transfer the file to the target path
    //image_file.transferTo(filePath.toFile());
    File file_to_save = new File("hello");
    image_file.transferTo(file_to_save.toPath());
}

  //In `/create` url creates new database entry for listing and image of the same listing 
  private void create_query_for_list(Listing listing) throws SQLException, IOException {    // make correction for category & subcategory if they are wrong
     String query = "INSERT INTO " + System.getenv("DB_TABLE_KP") + " (name, category, subcategory, description, price, currency) VALUES (?, ?, ?, ?, ?, ?)";
     String picture_query = "INSERT INTO " + System.getenv("DB_IMAGES_KP") + " (item_id, picture_url) VALUES (?, ?)";
     
     PreparedStatement pictureStmt;
    
     try{ 
      PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      pictureStmt = connection.prepareStatement(picture_query); 
      preparedStatement.setString(1, listing.name);
      preparedStatement.setString(2, listing.category);
      preparedStatement.setString(3, listing.subcategory);
      preparedStatement.setString(4, listing.description);
      preparedStatement.setString(5, Integer.toString(listing.price));
      preparedStatement.setString(6, listing.currency);
    
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
      
      
      for (MultipartFile imageFile : listing.imageFiles ) {
	      String fileName = imageFile.getOriginalFilename();
	      String filePath = "images/" + item_id + "/" + fileName;
	     
	      place_image(item_id, imageFile);
             
	  //  images.add(filePath);
             
	      pictureStmt.setInt(1, item_id);
              pictureStmt.setString(2, filePath);
              pictureStmt.executeUpdate();
              }
     
      
     /* 
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
      */
     } catch (SQLException e) {
    e.printStackTrace();
    }
  }
}
