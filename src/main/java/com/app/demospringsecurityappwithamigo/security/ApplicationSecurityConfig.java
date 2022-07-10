package com.app.demospringsecurityappwithamigo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.app.demospringsecurityappwithamigo.security.ApplicationEnumRole.ADMIN;
import static com.app.demospringsecurityappwithamigo.security.ApplicationEnumRole.STUDENT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;


    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails userStudentUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("anna"))
                .roles(STUDENT.name()) // will be stored as  ROLE_STUDENT
                .build();

        UserDetails lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("lin"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(userStudentUser, lindaUser);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers("/", "index","/css/*", "/js/*").permitAll()
                    .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


}
