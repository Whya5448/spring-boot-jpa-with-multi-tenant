package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final
    ApptomoUserDetailsService apptomoUserDetailsService;

    @Autowired
    public SecurityConfig(ApptomoUserDetailsService apptomoUserDetailsService) {
        this.apptomoUserDetailsService = apptomoUserDetailsService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
            "/c3/**",
            "/css/**",
            "/fonts/**",
            "/images/**",
            "/js/**",
            "/plugins/**",
            "/resource/**",
            "/ui/**",
            "/favicon.ico");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .formLogin()
            .loginPage("/sign/sex")
            .usernameParameter("username")
            .passwordParameter("password")
            .loginProcessingUrl("/login/process/stage/*")
            .defaultSuccessUrl("/d1")
            .and()
            .authorizeRequests()
            .antMatchers(
                "/",
                "/*/account/**",
                "/sign/**"
            )
            .permitAll()
            .and()
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/**")
            .permitAll()
            .and()
            .headers()
            .httpStrictTransportSecurity()
            .maxAgeInSeconds(0)
            .includeSubDomains(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(apptomoUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
        return delegatingPasswordEncoder;
    }
}
