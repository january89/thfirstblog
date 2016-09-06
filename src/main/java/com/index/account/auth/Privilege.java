package com.index.account.auth;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by eight on 2016-09-06.
 *
 */
@Entity
public class Privilege{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long sequence;
  private String name;
  @ManyToMany(mappedBy = "privileges")
  private Collection<Role> roles;
  protected Privilege(){}
  protected Privilege(String name){this.name = name;}

  public String getName() {
    return name;
  }
//  private enum Permit{
//    READ, WRITE, MODIFY, DELETE
//  }
}