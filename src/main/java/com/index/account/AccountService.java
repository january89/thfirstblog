package com.index.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.System.out;

/**
 * Created by eight on 2016-09-05.
 *
 */
@Service
class AccountService{

  private AccountRepository accountRepository;

  @Autowired
  private void setAccountRepository(AccountRepository accountRepository){
    this.accountRepository = accountRepository;
  }

  // Save
  public Account save(Account account){
    out.println(account.toString());
    out.println(account);
    return accountRepository.save(account);
  }
}