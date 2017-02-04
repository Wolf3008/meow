package com.mycompany.meowcrm.config;

import com.mycompany.meowcrm.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(getCustomUserDetailsService())
                .passwordEncoder(getShaPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login/**", "/res/**").permitAll()
                //.antMatchers("/app/resources/**").anonymous()
                .and()
                .csrf().disable();

        http.authorizeRequests()
                .antMatchers("/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                //.antMatchers("/admin").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login/login")
                .failureUrl("/login/filed")
                .loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/menu")
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll();

        http.logout()
                .logoutUrl("/login/logout")
                .logoutSuccessUrl("/login/login");

    }

    @Autowired
    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder() {
        return new ShaPasswordEncoder();
    }

    @Autowired
    @Bean
    public CustomUserDetailsService getCustomUserDetailsService() {
        return new CustomUserDetailsService();
    }
}
