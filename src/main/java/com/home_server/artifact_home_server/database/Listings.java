package com.home_server.artifact_home_server.database;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class Listings {
  public Database_instance database;
  public Connection connection;
  public String listings_table;

  Listings() throws SQLException{
    this.listings_table = System.getenv("DB_TABLE_KP");
    this.database= Database_instance.getInstance();
    this.connection = database.getConnection();
  }

  public List<Item> getAllItems() throws SQLException{
    List<Item> listing = new ArrayList<>();
    String query = "SELECT * FROM " + listings_table;
    return null;
  }
}
