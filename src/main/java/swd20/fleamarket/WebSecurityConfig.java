package swd20.fleamarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import swd20.fleamarket.web.UserDetailServiceImpl;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers("/css/**", "/img/**", "/h2-console/**").permitAll() // h2-console näkyviin, kun Spring Security asetukset on päällä
        // .permitAll() - endpoint, jonka kaikki käyttäjät näkevät
        .and().csrf().ignoringAntMatchers("/h2-console/**")
        .and().headers().frameOptions().sameOrigin()
        .and()
        .authorizeRequests()
        .antMatchers("/", "/contact", "/reservationlist", "/search").permitAll() // kaikki käyttäjät pääsevät käsiksi näihin endpointeihin
        .antMatchers("/newreservation, /savereservation, /savecustomer, /newcustomer, /editreservation/{id}", "/deletereservation/{id}").hasAuthority("ADMIN") // admin pääsee käsiksi vain näihin määrittyihin endpointeihin
          .anyRequest().authenticated() // kirjautuneet pääsevät muihin pyyntöihin käsiksi, paitsi yllämainittuun
          .and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/reservationlist")
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    
    }

}