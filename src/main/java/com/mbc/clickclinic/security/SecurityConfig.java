package com.mbc.clickclinic.security;

import com.mbc.clickclinic.service.MedecinDetailsService;
import com.mbc.clickclinic.service.PatientDetailsService;
import com.mbc.clickclinic.service.SecretaireDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MedecinDetailsService medecinDetailsService;

    public SecurityConfig(MedecinDetailsService medecinDetailsService) {
        this.medecinDetailsService = medecinDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(medecinDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/adminDashboard").and().logout().logoutUrl("/logout");
        http.authorizeRequests().antMatchers("/resources/**","/static/**","/resources/templates/include/**","/assets/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/admin").hasAuthority("ADMIN").antMatchers("/login","/chatRoom","/getResponse","/resources/**","/static/**","/resources/templates/include/**","/assets/**","/include/**","/static/assets/js/**","/static/assets/css/**","/static/assets/scss/**").permitAll()
                .antMatchers("/resources/**","/static/**","/include/**").permitAll()
                .anyRequest().authenticated().and().exceptionHandling().accessDeniedPage("/forbidden");
    }
}
