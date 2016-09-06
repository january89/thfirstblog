package com.index.account;

import com.index.account.auth.Role;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by eight on 2016-09-05.
 *
 */
@Entity
@Table(name = "ACCOUNT")
public class Account{
  protected Account(){
    this.timestamp = new Date();
  }
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long sequence;
  @Column(name = "FIRSTNAME")
  private String firstName;
  @Column(name = "LASTNAME")
  private String lastName;
  @Column(name = "EMAIL", nullable = false)
  private String eMail;
  @Column(name = "PASSWORD", nullable = false)
  private String password;
  private boolean enabled;
  private boolean tokenExpired;
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  @ManyToMany
  @JoinTable(
      name = "ACCOUNT_ROLES",
      joinColumns = @JoinColumn(name = "ACCOUNT_SEQ", referencedColumnName = "SEQUENCE"),
      inverseJoinColumns = @JoinColumn(name = "ROLE_SEQ", referencedColumnName = "SEQUENCE"))
  private Collection<Role> roles;

  public String getFirstName(){
    return firstName;
  }
  public void setFirstName(String firstName){
    this.firstName = firstName;
  }
  public String getLastName(){
    return lastName;
  }
  public void setLastName(String lastName){
    this.lastName = lastName;
  }
  public String geteMail(){
    return eMail;
  }
  public void seteMail(String eMail){
    this.eMail = eMail;
  }
  public String getPassword(){
    return password;
  }
  public void setPassword(String password){
    this.password = password;
  }
  public boolean isEnabled(){return true;}

  public Collection<Role> getRoles() {
    return roles;
  }
}
