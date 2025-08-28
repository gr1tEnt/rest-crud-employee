package com.gr1tEnt.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfiguration {

    @Bean
    public UserDetailsManager detailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChai(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configer ->
                configer
                        .requestMatchers(HttpMethod.GET, "/api/employees/").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // use HTTP basic configuration
        http.httpBasic(Customizer.withDefaults());


        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}

/*
@Bean
public InMemoryUserDetailsManager userDetailsManager() {
    UserDetails john = User.builder()
            .username("John")
            .password("{noop}pass123")
            .roles("EMPLOYEE")
            .build();

    UserDetails mary = User.builder()
            .username("mary")
            .password("{noop}pass123")
            .roles("EMPLOYEE", "MANAGER")
            .build();

    UserDetails alex = User.builder()
            .username("alex")
            .password("{noop}pass123")
            .roles("EMPLOYEE", "MANAGER", "ADMIN")
            .build();

    return new InMemoryUserDetailsManager(john, mary, alex);
}*/
