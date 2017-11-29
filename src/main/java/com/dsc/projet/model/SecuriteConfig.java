/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.model;

import javax.inject.Inject;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

/**
 *
 * @author Smaïl
 */
@Configuration
@EnableWebSecurity
public class SecuriteConfig extends WebSecurityConfigurerAdapter{
    
    @Inject
    UserServices userDetailsService;
    
    
    
    
    protected void configure(HttpSecurity http) throws Exception {
     
             http.authorizeRequests()
               .antMatchers("/","/css/style.css","/css/picnic.css" ,"/images/welcome.jpg","/Welcome","/homeStudent","/addStudent","/addCompany").permitAll()

                .anyRequest().authenticated() //.hasAnyRole("ADMIN")
                
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                .and()
                .formLogin().defaultSuccessUrl("/profile")// je crée un controleur dans la page profil pour lui indiquer kel page doit y alle acueil student acuell compâny
                     
                //.loginPage("/login")
                .permitAll()
                .and()
                .csrf().ignoringAntMatchers("/api/**");
                http.csrf().disable();

    }
   
   
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
		 .userDetailsService(userDetailsService)
		  .passwordEncoder(userDetailsService.encoder);
               // .inMemoryAuthentication()
                //.withUser("robert").password("tata").roles("USER");
	}


   
    
    
    
}
