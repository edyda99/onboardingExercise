package com.eurisko.onboardingexercise.project.container.security;

import com.eurisko.onboardingexercise.project.container.security.filter.CustomAuthenticationFilter;
import com.eurisko.onboardingexercise.project.container.security.filter.CustomAuthorizationFilter;
import com.eurisko.onboardingexercise.project.module.integration.repo.LoginUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoginUserRepo loginUserRepo;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            //LoginUser user = loginUserRepo.findByUsername(username);
            UserDetails admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles("ADMIN")
                    .build();
//            if (user == null) {
//                log.error("User Not Found");
//                throw new UsernameNotFoundException("User not found in the database");
//            } else {
//                log.info("User found in the database {}", username);
//            }
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            user.getRoles().forEach(role ->
//                    authorities.add(new SimpleGrantedAuthority(role.getName()))
//            );
//            return new User(user.getUsername(), user.getPassword(), authorities);
            return new User(admin.getUsername(), admin.getPassword(), admin.getAuthorities());
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CommandLineRunner run(LoginUserRepo repo, RoleRepo roleRepo) {
//        return args -> {
//            roleRepo.save(new Role().setName("ADMIN"));
//            repo.save(new LoginUser()
//                    .setName("admin")
//                    .setUsername("admin")
//                    .setPassword("admin")//TODO encode the password before saving
//                    .setRoles(roleRepo.findAll()));
//        };
//    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();//TODO enable after using postmand.
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/v1/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/api/v1/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
