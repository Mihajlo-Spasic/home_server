package com.home_server.artifact_home_server.database;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

  public getDatabase() throws SQLException{
    return instance = Database_instance.getInstance();
  }

  public List<item> getAllItems() throws SQLException{
    List<item> = new ArrayList();
    String query = "SELECT * FROM " + listings_table;
  }
}
