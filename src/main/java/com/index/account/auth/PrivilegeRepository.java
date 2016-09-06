package com.index.account.auth;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by eight on 2016-09-06.
 *
 */
public interface PrivilegeRepository extends JpaRepository<Privilege,Long>{
  Privilege findByName(String name);
}