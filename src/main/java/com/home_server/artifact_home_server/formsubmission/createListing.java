package com.home_server.artifact_home_server.formsubmission;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.home_server.artifact_home_server.database.Database_instance;
import com.home_server.artifact_home_server.database.database_kp_logic;

@Controller
public class createListing {

  public Database_instance database;
  public Connection connection;
  public Statement statement;
  public ResultSet resultSet;

  @GetMapping("/create")
  public String create_listing(Model model) throws SQLException {
    model.addAttribute("listing", new Listing());

    return "/create";
  }

  // Event triggers on form submission passing the listing object with form data
  // in the "new_listing" variable
  @PostMapping(value = "/create", consumes = "multipart/form-data")
  public String list_submit(@ModelAttribute Listing new_listing, Model model) throws SQLException, IOException {

    model.addAttribute("listing", new_listing);

    database = Database_instance.getInstance();
    connection = database.getConnection();
    statement = connection.createStatement();

    create_query_for_list(new_listing);

    return "redirect:/kp";
  }

  // Makes an entry for each image passed in /create form with the id of the
  // latest listing entry/object
  public void make_directories_for_entries(int item_id) {
    File dir = new File("images/" + item_id);
    if (!dir.exists()) {
      dir.mkdirs();
    }
  }

  // Method to place the image into the corresponding directory
  public void place_image(int item_id, MultipartFile image_file) throws IOException {
    make_directories_for_entries(item_id);

    String uploadDir = "images/" + item_id + "/";

    Path filePath = Paths.get(uploadDir, image_file.getOriginalFilename());
    image_file.transferTo(filePath);
  }

  // In `/create` url creates new database entry for listing and image of the same
  // listing
  private void create_query_for_list(Listing listing) throws SQLException, IOException { // make correction for category
                                                                                         // & subcategory if they are
                                                                                         // wrong

    String query = "INSERT INTO " + System.getenv("DB_TABLE_KP")
        + " (name, category, subcategory, description, price, currency) VALUES (?, ?, ?, ?, ?, ?)";
    String picture_query = "INSERT INTO " + System.getenv("DB_IMAGES_KP") + " (item_id, picture_url) VALUES (?, ?)";

    PreparedStatement pictureStmt;
    PreparedStatement preparedStatement;

    try {
      preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      pictureStmt = connection.prepareStatement(picture_query);

      preparedStatement.setString(1, listing.name);
      preparedStatement.setString(2, listing.category);
      preparedStatement.setString(3, listing.subcategory);
      preparedStatement.setString(4, listing.description);
      preparedStatement.setString(5, Integer.toString(listing.price));
      preparedStatement.setString(6, listing.currency);

      preparedStatement.executeUpdate();

      // Getting the id of the current(latest) database entry
      ResultSet generatedId = preparedStatement.getGeneratedKeys();
      int item_id = 0;
      if (generatedId.next()) {
        item_id = generatedId.getInt(1);
      } else {
        System.err.println("item_id call failed [createListing.java:75]");
        return;
      }

      for (MultipartFile imageFile : listing.imageFiles) {
        String fileName = imageFile.getOriginalFilename();
        String filePath = "/images/" + item_id + "/" + fileName;

        place_image(item_id, imageFile);

        pictureStmt.setInt(1, item_id);
        pictureStmt.setString(2, filePath);
        pictureStmt.executeUpdate();

      }
      // database_kp_logic db_kp = new database_kp_logic();
      // db_kp.kp_check_database_and_make_kp_listing();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
