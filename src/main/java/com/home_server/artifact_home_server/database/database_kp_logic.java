package com.home_server.artifact_home_server.database;

import com.home_server.artifact_home_server.listinghandling.Kupujem_Prodajem_instance;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class database_kp_logic {

    public Database_instance database;
    public Kupujem_Prodajem_instance browser;

    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;

    public database_kp_logic() throws SQLException {
        database = Database_instance.getInstance();
        browser = Kupujem_Prodajem_instance.getInstance();
    }

    public void kp_check_database_and_make_kp_listing() throws SQLException {
        // query database
        // Perhaps make this be a class wide variables i.e part of singleton as to lower
        // the method execution time (huge improvement)
        List<String> db_listing_names = database.getAllListingNames();
        List<String> kp_listing_names = browser.getAllListingNames();

        if (db_listing_names.isEmpty()) {
            System.out.println("database empty");
            return;
        }
        if (kp_listing_names.isEmpty()) {
            System.out.println("kp listing empty");
            return;
        }

        db_listing_names.removeAll(kp_listing_names);

        // Loop throught database listings and create a kp_listing if said listing is
        // not included in kp_listing_names list
        for (int i = 0; i < db_listing_names.size(); i++) {
            String db_query = "SELECT * FROM " + System.getenv("DB_TABLE_KP") + " WHERE name = "
                    + db_listing_names.get(i);

            connection = database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(db_query);

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String category = resultSet.getString("category");
            String subcategory = resultSet.getString("subcategory");
            String description = resultSet.getString("description");
            int price = resultSet.getInt("price");
            String currency = resultSet.getString("currency");
            // query current db_listing_name and make a listing on kp

            String picture_query = "SELECT * FROM " + System.getenv("DB_IMAGES_KP") + "WHERE id = "
                    + String.valueOf(id);

            Statement picture_statement = connection.createStatement();
            ResultSet picture_resultSet = statement.executeQuery(picture_query);

            List<String> image_paths = new ArrayList<String>();
            while (picture_resultSet.next()) {
                image_paths.add(picture_resultSet.getString("picture_url"));
            }
            browser.createListingTemplate(name, image_paths, price, description, currency);

        }
    }
}
