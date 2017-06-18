package com.ballistic.security.Securitytest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Lycus 01 on 6/18/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Ballistic").password("Ballistic").roles("ADMIN","USER","DBA");
        auth.inMemoryAuthentication().withUser("Pakistan").password("Pakistan").roles("USER", "DBA");
        auth.inMemoryAuthentication().withUser("Zindabad").password("Zindabad").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().and().authorizeRequests().
                antMatchers(HttpMethod.GET, "/read").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/delete/**").hasAnyRole("DBA")
                .and().csrf().disable();
    }
}
