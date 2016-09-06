package com.index.account.auth;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by eight on 2016-09-06.
 *
 */
public interface RoleRepository extends JpaRepository<Role,Long>{
  Role findByName(String name);
}
