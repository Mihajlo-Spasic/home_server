package com.home_server.artifact_home_server.formsubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.sql.*;


@Controller
public class createListing{

  @GetMapping("/create")
  public String create_listing(Model model) throws SQLException{
    model.addAttribute("listing",new Listing());
    return "/create";
  } 

  @PostMapping("/create")
  public String list_submit( @ModelAttribute Listing new_listing, Model model){ 
    model.addAttribute("listing", new_listing);
    return "redirect:/kp";
  }
  
 
}
