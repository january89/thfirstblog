package com.index.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by eight on 2016-09-05.
 *
 */
@Controller
class AccountController{
  private final String defaultPath = "/account";
  private AccountService accountService;

  @Autowired
  public void setAccountService(AccountService accountService){
    this.accountService = accountService;
  }

  @GetMapping("/login")
  private String loginForm(Model model){
    model.addAttribute("account",new Account());
    return defaultPath + "/login";
  }
  @PostMapping("/login")
  private String loginSubmit(Account account){
    System.out.println(account.geteMail());
    System.out.println(account.getPassword());
    return "/index";
  }
  @GetMapping("/register")
  private String registerForm(Model model){
    model.addAttribute("account",new Account());
    return defaultPath + "/register";
  }
  @PostMapping("/register")
  private String registerSubmit(@ModelAttribute Account account){
    accountService.save(account);
    return "/index";
  }

}