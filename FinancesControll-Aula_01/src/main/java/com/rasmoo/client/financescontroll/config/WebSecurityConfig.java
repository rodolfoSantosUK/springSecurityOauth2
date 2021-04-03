package com.rasmoo.client.financescontroll.config;

import com.rasmoo.client.financescontroll.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configuracaoGlobal(AuthenticationManagerBuilder auth, UserInfoService userInfoService) throws Exception {
       auth.userDetailsService(userInfoService).passwordEncoder(this.encoder()) ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] allowed = new String[]{
                "/webjars", "/v1/usuario", "static/**"
        };

        http.csrf().disable().authorizeRequests()
                .antMatchers(allowed).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

}