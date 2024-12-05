package com.home_server.artifact_home_server.database;

import java.util.List;

public class Item{
  private int Id;
  private String Name;
  private String Cathegory;
  private String SubCathegory;
  private String Description;
  private List<Strings> images;
 

  Items(int id, String name, String cathegory, String subcathegory, String description){
    this.Id = id;
    this.Name = name;
    this.Cathegory = cathegory;
    this.SubCathegory = subcathegory;
    this.Description = description;
    Item_image image_loader = new Item_image(id);
    this.images = getImages(id);
  }

  public int getIdint(){
    return id;
  }
  
  public String getIdStr(){
    return String.valueOf(id);
  }
  
  public String getCathegory(){
    return cathegory;
  }
  
  public String getSubCathegory(){
    return subcathegory;
  }
  
  public String getDescription(){
    return description;
  }

  public List<Strings> getImages(int id){
    return image_loader.getImages(id)
  }
}
