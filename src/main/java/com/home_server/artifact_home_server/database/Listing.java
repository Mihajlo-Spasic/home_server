package com.home_server.artifact_home_server.database;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class Listing {
  public Database_instance database;
  public Connection connection;
  public String listings_table;

  public Listing() throws SQLException{
    this.listings_table = System.getenv("DB_TABLE_KP");
    this.database= Database_instance.getInstance();
    this.connection = database.getConnection();
  }

  public List<Item> getAllItems() throws SQLException{
    List<Item> listing = new ArrayList<>();
    String query = "SELECT * FROM " + listings_table;    
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);

    while (resultSet.next()){
      Item item = new Item(resultSet.getInt("id"),
                           resultSet.getString("name"),
                           resultSet.getString("category"),
                           resultSet.getString("subcategory"),
                           resultSet.getString("description"),
                           resultSet.getInt("price"),
                           resultSet.getString("currency"));
      listing.add(item);
    }
    return listing;
  }
  public Item getItemById(int id){
    return null;
  }
  public Item getItemByName(String name){
    return null;
  }
}
