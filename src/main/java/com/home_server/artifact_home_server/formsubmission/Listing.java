package home_server.artifact_home_server.formsubmission; 


public class Listing{
  
  private String name
  private String category;
  private String subcategory;
  private String description;
  private List<String> images;

  public String setName(String name){
    this.name = name
  }
  
  public String getName(){
    return name;
  }
  
  public String setCategory(String category){
    this.category = category;
  }
  
  public String getCategory(){
    return category;
  }

  public String setSubcategory(String subcategory){
    this.subcategory = subcategory;
  }

  public String getSubcategory(){
    return subcategory;
  }

  public String setDescription(String description){
    this.description = decription;
  }

  public String getDescription(){
    return description;
  }

  public List<String> setImages(List<String> images){
    return this.images;
  }

  public List<String> getImages(){
  return images;
  }
}
