package com.home_server.artifact_home_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database_instance {
  
  private static Database_instance instance;
  private Connection connection;
  
  private String mysql_port = System.getenv("MYSQL_PORT");
  private String mysql_database_name = System.getenv("MYSQL_DATABASE_NAME");
  private String mysql_url = "jdbc:mysql://localhost:" + mysql_port + "/" + mysql_database_name; 
  
  private String DB_USER = System.getenv("DB_USER");
  private String DB_PASSWORD = System.getenv("DB_PASSWORD");

  
  private Database_instance() throws SQLException{
    try{
      // Loading the JDBC Driver and connecting to the database with credentials
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.connection = DriverManager.getConnection(mysql_url, DB_USER, DB_PASSWORD);

    } catch (ClassNotFoundException e){
      throw new SQLException("MySQL Driver not found", e);
    }
  }
  
  public static Database_instance getInstance() throws SQLException{
    if (instance == null || instance.getConnection().isClosed()){
      return instance = new Database_instance();
    }
    return instance;
  }

  public Connection getConnection(){
    return connection;
  }
    
}
