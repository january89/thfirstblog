package com.index.account.auth;

import com.index.account.Account;
import com.index.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by eight on 2016-09-06.
 *
 */
@Service
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent>{

  private boolean alreadySetup = false;

  private AccountRepository accountRepository;
  @Autowired
  private void setAccountRepository(AccountRepository accountRepository){
    this.accountRepository = accountRepository;
  }
  private PrivilegeRepository privilegeRepository;
  @Autowired
  private void setPrivilegeRepository(PrivilegeRepository privilegeRepository){
    this.privilegeRepository = privilegeRepository;
  }
  private RoleRepository roleRepository;
  @Autowired
  private void setRoleRepository(RoleRepository roleRepository){
    this.roleRepository = roleRepository;
  }

  @Override @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event){
    if(alreadySetup) return;
    Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
    Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
    List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
    createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
    createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

//    Role adminRole = roleRepository.findByName("ROLE_ADMIN");
//    Account user = new Account();
//    user.setFirstName("Test");
//    user.setLastName("Test");
//    user.setPassword(passwordEncoder.encode("test"));
//    user.setEmail("test@test.com");
//    user.setRoles(Arrays.asList(adminRole));
//    user.setEnabled(true);
//    accountRepository.save(user);
    alreadySetup = true;
  }

  @Transactional
  private Role createRoleIfNotFound(String name, Collection<Privilege> privilege){
    Role role = roleRepository.findByName(name);
    if(role == null){
      role = new Role(name);
      role.setPrivileges(privilege);
      roleRepository.save(role);
    }
    return role;
  }

  @Transactional
  private Privilege createPrivilegeIfNotFound(String name){
    Privilege privilege = privilegeRepository.findByName(name);
    if(privilege == null){
      privilege = new Privilege(name);
      privilegeRepository.save(privilege);
    }
    return privilege;
  }

//  @Override
//  User registerNewUserAccount(Account account){
//    if(email)
//  }
}
