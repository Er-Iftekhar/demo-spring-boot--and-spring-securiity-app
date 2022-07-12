package com.app.demospringsecurityappwithamigo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.app.demospringsecurityappwithamigo.security.ApplicationEnumRole.*;
import static com.app.demospringsecurityappwithamigo.security.ApplicationUserPermissions.COURSE_WRITE;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;


    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails userStudentUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("anna"))
//                .roles(STUDENT.name()) // will be stored as  ROLE_STUDENT
                .authorities(STUDENT.grantedAuthorities())
                .build();

        UserDetails lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("lin"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.grantedAuthorities())
                .build();

        UserDetails tomUser = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("tom"))
//                .roles(ADMINTRAINEE.name()) // ROLE_ADMINTRAINEE
                .authorities(ADMINTRAINEE.grantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(userStudentUser, lindaUser, tomUser);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "index","/css/*", "/js/*").permitAll()
                    .antMatchers("/api/**").hasRole(STUDENT.name())
//                    .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                    .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                    .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                    .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(),ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


}
