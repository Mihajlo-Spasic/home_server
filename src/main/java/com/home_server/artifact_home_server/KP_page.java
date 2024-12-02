package com.home_server.artifact_home_server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class KP_page{
  
  Database_instance database = Database_instance.getInstance();    
  
  @GetMapping("/kp")
  public String test(){
    return "kp";
  }

  @PostMapping("/load_all_kp_advertisments")
  public String load_all_kp_advertisment() {
        System.out.println("load all adertisments called!");
        
        return "redirect:/kp"; 
    }
}
