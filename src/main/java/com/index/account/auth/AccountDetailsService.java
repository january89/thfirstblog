package com.index.account.auth;

import com.index.account.Account;
import com.index.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by eight on 2016-09-06.
 *
 */
@Service("userDatailsService")
public class AccountDetailsService implements UserDetailsService{
  private AccountRepository accountRepository;
  private RoleRepository roleRepository;
  @Autowired
  public void setAccountRepository(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }
  @Autowired
  public void setRoleRepository(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Account account = accountRepository.findByEmail(email);
    if(account == null)
      return new User("", "", true, true, true, true,getAuthorities(Collections.singletonList(roleRepository.findByName("ROLE_USER"))));

    return new User(account.geteMail(),account.getPassword(),account.isEnabled(),true,true,true,getAuthorities(account.getRoles()));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles){
    return getGrantedAuthorities(getPrivileges(roles));
  }
  private List<String> getPrivileges(Collection<Role> roles){
    List<String> privileges = new ArrayList<>();
    List<Privilege> collection = new ArrayList<>();
    for(Role role : roles)
      collection.addAll(role.getPrivileges());
    privileges.addAll(
        collection
            .stream()
            .map(Privilege::getName)
            .collect(Collectors.toList())
    );
    return privileges;
  }
  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges){
    return privileges
        .stream()
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }
}