package com.home_server.artifact_home_server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.sql.SQLException;




@Controller
public class KP_page{
  public Database_instance database; 
  {
    try{
      database = Database_instance.getInstance();
    } catch(SQLException exception){
      exception.printStackTrace();
    }
  }
  
  @GetMapping("/kp")
  public String test(){
    //returns a file kp.html
    return "kp";
  }

  @PostMapping("/load_all_kp_advertisments")
  public String load_all_kp_advertisment() {
        System.out.println("load all adertisments called!");
        
        return "redirect:/kp"; 
    }
}
