package by.epam.crackertracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.security.AuthProvider;
import java.util.*;
import java.util.ArrayList;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        List<UserDetails> users = new ArrayList<>();
        users.add(User.withDefaultPasswordEncoder().username("Andrey").password("Ferdinand2").roles("USER").build());

        return new InMemoryUserDetailsManager(users);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
              //  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              //  .and()
                .authorizeRequests().antMatchers("/","/about", "/review/show", "/user/registration", "/lang", "/user/login", "/test").permitAll()
                .anyRequest().authenticated()
                .and();

        httpSecurity.formLogin().loginPage("/login")
               // .loginProcessingUrl("/j_spring_security_check").failureUrl("/login?error").usernameParameter("j_login")
               // .passwordParameter("j_password")
                .permitAll();

        httpSecurity.logout()
                // .logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true)
        .permitAll();

    }

}
