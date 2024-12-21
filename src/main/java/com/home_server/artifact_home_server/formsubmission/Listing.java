package com.home_server.artifact_home_server.formsubmission; 

import java.util.List;

public class Listing{
  
  public String name;
  public String category;
  public String subcategory;
  public String description;
  public List<String> images;

  public void setName(String name){
    this.name = name;
  };
  
  public String getName(){
    return name;
  };
  
  public void setCategory(String category){
    this.category = category;
  };
  
  public String getCategory(){
    return category;
  };

  public void setSubcategory(String subcategory){
    this.subcategory = subcategory;
  };

  public String getSubcategory(){
    return subcategory;
  };

  public void setDescription(String description){
    this.description = description;
  };

  public String getDescription(){
    return description;
  };

  public void setImages(List<String> images){
    this.images = images;
  };

  public List<String> getImages(){
    return images;
  };
}
