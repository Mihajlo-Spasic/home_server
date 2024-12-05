package com.home_server.artifact_home_server.database;

import java.util.List;
import java.sql.*;

public class Item{
  private int Id;
  private String Name;
  private String Cathegory;
  private String SubCathegory;
  private String Description;
  private List<String> images;
  private Item_image image_loader; 

  public Item(int id, String name, String cathegory, String subcathegory, String description) throws SQLException{
    this.Id = id;
    this.Name = name;
    this.Cathegory = cathegory;
    this.SubCathegory = subcathegory;
    this.Description = description;
    Item_image image_loader = new Item_image(id);
    this.images = getImages(id);
  }

  public int getIdint(){
    return Id;
  }
  
  public String getIdStr(){
    return String.valueOf(Id);
  }
  
  public String getCathegory(){
    return Cathegory;
  }
  
  public String getSubCathegory(){
    return SubCathegory;
  }
  
  public String getDescription(){
    return Description;
  }

  public List<String> getImages(int id) throws SQLException{
    return image_loader.getImages(id);
  }
}
