package com.ballistic.security.Securitytest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Lycus 01 on 6/18/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Ballistic").password(bCryptPasswordEncoder.encode("Ballistic")).roles("ADMIN","USER","DBA");
        auth.inMemoryAuthentication().withUser("Pakistan").password(bCryptPasswordEncoder.encode("Pakistan")).roles("USER", "DBA");
        auth.inMemoryAuthentication().withUser("Zindabad").password(bCryptPasswordEncoder.encode("Zindabad")).roles("ADMIN");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().and().authorizeRequests().
                antMatchers(HttpMethod.GET, "/homePage").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')").
                antMatchers(HttpMethod.GET, "/read").hasAnyRole("USER", "ADMIN", "DBA").
                antMatchers(HttpMethod.DELETE, "/delete/**").hasAnyRole("DBA").
                antMatchers(HttpMethod.POST, "/add").hasAnyRole("DBA").
                antMatchers(HttpMethod.PUT, "/update/**").hasAnyRole("DBA").
                antMatchers(HttpMethod.DELETE, "/delete/**").hasAnyRole("DBA").and().
                formLogin().loginPage("/loginPage").
                defaultSuccessUrl("/homePage").
                failureUrl("/loginPage?error").
                usernameParameter("username").passwordParameter("password").and().
                logout().logoutSuccessUrl("/loginPage?logout")
                .and().csrf().disable();
    }
}
