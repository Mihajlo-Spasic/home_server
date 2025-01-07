package com.home_server.artifact_home_server.database;

import com.home_server.artifact_home_server.listinghandling.Kupujem_Prodajem_instance;
import java.sql.*;
import java.util.List;

public class database_kp_logic {

    Database_instance database;
    Kupujem_Prodajem_instance browser;

    public database_kp_logic() throws SQLException {
        database = Database_instance.getInstance();
        browser = Kupujem_Prodajem_instance.getInstance();
    }

    public void kp_check_database_and_make_kp_listing() {
        // query database
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

            // query current db_listing_name and make a listing on kp

            browser.createListingTemplate();
        }
    }
}
