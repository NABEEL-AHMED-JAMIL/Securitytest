package com.ballistic.security.Securitytest.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Ballistic Inc on 6/18/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.httpBasic().and().
                authorizeRequests().
                antMatchers(HttpMethod.GET, "/homePage").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')").
                antMatchers(HttpMethod.GET, "/read").hasAnyRole("USER", "ADMIN", "DBA").
                antMatchers(HttpMethod.POST, "/add").hasRole("DBA").
                antMatchers(HttpMethod.PUT, "/update/**").hasRole("DBA").
                antMatchers(HttpMethod.POST, "/register").hasRole("DBA").
                antMatchers(HttpMethod.DELETE, "/delete/**").hasRole("DBA").and().
                formLogin().loginPage("/loginPage").defaultSuccessUrl("/homePage").failureUrl("/login?error").
                usernameParameter("username").permitAll().and().
                logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll().and().
                exceptionHandling().accessDeniedPage("/403").and().csrf().disable();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}

