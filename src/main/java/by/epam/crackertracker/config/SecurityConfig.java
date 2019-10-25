package by.epam.crackertracker.config;

import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@ComponentScan(PageConstant.PROJECT)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authorizeRequests().antMatchers(PageConstant.PATH_PAGE_INDEX, PageConstant.PATH_REVIEW + PageConstant.URI_SHOW_MAIN,
                PageConstant.REQUEST_USER + PageConstant.URI_REGISTER, PageConstant.URI_LANG, PageConstant.PATH_PICTURE,
                PageConstant.URI_RESOURCES, PageConstant.URI_ABOUT).permitAll()

                //ADMINS
                .antMatchers(PageConstant.SECUR_PATH_UPDATE_ROLE, PageConstant.SECUR_PATH_REVIEW_DELETE)
                .hasRole(ParameterConstant.ROLE_ADMIN)

                //ADMINS + CURATORS
                .antMatchers(PageConstant.SECUR_PATH_ADVICE_ADD, PageConstant.SECUR_PATH_ADVICE_DELETE, PageConstant.SECUR_PATH_PRODUCT_ADD,
                        PageConstant.SECUR_PATH_PRODUCT_DELETE, PageConstant.SECUR_PATH_PRODUCT_UPDATE)
                .hasAnyRole(ParameterConstant.ROLE_ADMIN, ParameterConstant.ROLE_CURATOR)
                .anyRequest().authenticated()

                .and();

        httpSecurity.formLogin().loginPage(PageConstant.URI_LOGIN)
                .permitAll();

        httpSecurity.logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher(PageConstant.LOGOUT)).logoutSuccessUrl(PageConstant.LOGOUT_SUCCESS)
        .permitAll();

    }
}
