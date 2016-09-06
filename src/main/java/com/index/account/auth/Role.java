package com.index.account.auth;

import com.index.account.Account;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by eight on 2016-09-06.
 *
 */
@Entity
public class Role{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long sequence;
  private String name;
  @ManyToMany(mappedBy = "roles")
  private Collection<Account> accounts;

  @ManyToMany
  @JoinTable(
      name = "ROLES_PRIVILEGES",
      joinColumns = @JoinColumn(name = "ROLE_SEQ", referencedColumnName = "SEQUENCE"),
      inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_SEQ", referencedColumnName = "SEQUENCE"))
  private Collection<Privilege> privileges;

  protected Role(){}
  protected Role(String name){this.name = name;}

  void setPrivileges(Collection<Privilege> privileges) {
    this.privileges = privileges;
  }
  protected Collection<Privilege> getPrivileges(){
    return privileges;
  }
  //  public enum RoleType{
//    ROLE_ADMIN, ROLE_USER
//  }
}
