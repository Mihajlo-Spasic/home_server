package com.home_server.artifact_home_server.formsubmission; 

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class Listing{
  
  public String name;
  public String category;
  public String subcategory;
  public String description;
  public String currency;
  public List<String> images;
  public List<MultipartFile> imagesMP;
  public int price; 
  
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
  
  public void setPrice(int price){
    this.price = price;
  }
  
  public int getPrice(){
    return price;
  }

  public void setCurrency(String currency){
    this.currency = currency;
  }
  
  public String getCurrency(){
    return currency;
  }
  
  public void setImagesMP(List<MultipartFile> imagesMP){
    this.imagesMP = imagesMP;
  }

  public List<MultipartFile> getImagesMP(){
    return imagesMP;
  }
}
