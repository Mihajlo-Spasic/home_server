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
  private int price;
  private String currency;

  public Item(int id, String name, String category, String subcategory, String description, int price, String currency) throws SQLException{
    this.id = id;
    this.name = name;
    this.category = category;
    this.subCategory= subcategory;
    this.description = description;
    this.image_loader = new Item_image(id);
    this.images = getImages(id);
    this.price = price;
    this.currency = currency;
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
    
    public String getCurrency() {
      return currency;
    }
  
    public int getPrice() {
      return price;
    }
}
