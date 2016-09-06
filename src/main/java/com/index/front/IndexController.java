package com.index.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eight on 2016-09-05.
 *
 */
@Controller
@RequestMapping({"/","/index"})
public class IndexController {
  @GetMapping
  private String index(){
    return "/index";
  }
//  @GetMapping
//  private String index(Model model){
//    return "/index";
//  }
}