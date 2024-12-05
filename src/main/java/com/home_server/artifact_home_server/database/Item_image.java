package com.home_server.artifact_home_server.database;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class Item_image{
  private List<String> Image_path = new ArrayList<>();
  private int Id = 0;
  public String DB_PICTURES_KP= System.getenv("DB_IMAGES_KP");
  public String column_name_images = "picture_url";
  Database_instance database;
  Connection connection;

  public Item_image() throws SQLException{
    database = Database_instance.getInstance();    
    connection = database.getConnection();
  }
  public Item_image(int id) throws SQLException{
    this.Id = id;
    database = Database_instance.getInstance();    
    connection = database.getConnection();
  }
  public List<String> getImages() throws SQLException{
    if (Id == 0){
      Image_path.clear();
      String query = "SELECT "+ column_name_images + "FROM " + DB_PICTURES_KP + "Where id=" + String.valueOf(Id);
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      
      while (resultSet.next()){
        Image_path.add(resultSet.getString(column_name_images));
      }
      return Image_path;
    }
    System.err.println("ID NEEDED FOR QUERY [ERROR IN FILE Item_image]");
    return null;
  }  
  
  public List<String> getImages(int item_id) throws SQLException{
    Image_path.clear();
    String query = "SELECT " + column_name_images + " FROM " + DB_PICTURES_KP + " WHERE id=" + item_id;
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);

    while (resultSet.next()) {
        Image_path.add(resultSet.getString(column_name_images));
    }
    return Image_path;
  }
}
