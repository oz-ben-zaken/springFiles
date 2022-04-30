package com.demo.DrFlight.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT \"username\",\"password\", true FROM \"users\" WHERE \"username\" =?")
                .authoritiesByUsernameQuery("SELECT CONCAT(\"username\", ' ',\"users\".\"id\"),\"role_name\" FROM \"users\" JOIN \"user_roles\" ON \"users\".\"user_role\" = \"user_roles\".\"id\"  WHERE \"username\" =?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .antMatchers("/admin/**").hasRole("administrator")
                .antMatchers("/airline/**").hasRole("airline company")
                .antMatchers("/customer/**").hasRole("customer")
                .antMatchers("/**").permitAll() //allow any user to connect to the page
                .and().formLogin();
    }
}
