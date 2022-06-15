package com.mbc.clickclinic.security;

import com.mbc.clickclinic.service.PatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//       prePostEnabled = true,
//        securedEnabled = true
//)
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class PatientSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PatientDetailsService patientDetailsService;

    @Autowired
    public PatientSecurityConfig(PatientDetailsService patientDetailsService){
        this.patientDetailsService = patientDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(patientDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().usernameParameter("email").passwordParameter("password");
        http.authorizeRequests().antMatchers("/resources/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/admin").hasAuthority("ADMIN").antMatchers("/login").permitAll()
                .anyRequest().authenticated().and().exceptionHandling().accessDeniedPage("/forbidden");
    }
}
