package com.index.account;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by eight on 2016-09-05.
 *
 */
public interface AccountRepository extends JpaRepository<Account,Long>{
  Account findByEmail(String eMail);
}