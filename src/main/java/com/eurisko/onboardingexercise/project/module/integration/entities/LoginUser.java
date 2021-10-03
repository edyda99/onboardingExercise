package com.eurisko.onboardingexercise.project.module.integration.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class LoginUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;
   @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = Collections.synchronizedSet(new HashSet<>());

   public LoginUser addRole(Role role){
       if(role==null) return this;
       roles.add(role);
       return this;
   }

}
