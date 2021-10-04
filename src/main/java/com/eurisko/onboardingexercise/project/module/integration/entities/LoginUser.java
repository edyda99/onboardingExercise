package com.eurisko.onboardingexercise.project.module.integration.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;


@Document
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class LoginUser {
    @Id
    private Long id;
    private String name;
    private String username;
    private String password;
    private Collection<Role> roles = Collections.synchronizedSet(new HashSet<>());

   public LoginUser addRole(Role role){
       if(role==null) return this;
       roles.add(role);
       return this;
   }

}
