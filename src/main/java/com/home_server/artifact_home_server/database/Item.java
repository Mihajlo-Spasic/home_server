package com.home_server.artifact_home_server.database;

import java.util.List;
import java.sql.*;

public class Item{
  private int id;
  private String name;
  private String category;
  private String subCategory;
  private String description;
  private List<String> images;
  private Item_image image_loader; 

  public Item(int id, String name, String category, String subcategory, String description) throws SQLException{
    this.id = id;
    this.name = name;
    this.category = category;
    this.subCategory= subcategory;
    this.description = description;
    this.image_loader = new Item_image(id);
    this.images = getImages(id);
  }

   public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subCategory;
    }

    public String getDescription() {
        return description;
    }
  public List<String> getImages(int id) throws SQLException{
    return image_loader.getImages(id);
  }
}
