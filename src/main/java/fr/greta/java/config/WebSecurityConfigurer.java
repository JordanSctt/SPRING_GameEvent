package fr.greta.java.config;

import fr.greta.java.user.persistence.SpringUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SpringUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/accueil")
                .authenticated()
                .antMatchers("/groupe/accueil")
                .authenticated()
                .antMatchers("/file/upload/groupe")
                .authenticated()
                .antMatchers("/file/upload/user")
                .authenticated()
                .antMatchers("/user/accueil/invitation")
                .authenticated()
                .antMatchers("/groupe/**")
                .authenticated()
                .antMatchers("/game/admin/**")
                .hasAuthority("ADMIN")
                .antMatchers("/game/list")
                .authenticated()
                .antMatchers("/accueil.html")
                .permitAll()
                .antMatchers("/signin")
                .permitAll()
                .antMatchers("/login")
                .permitAll()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user/accueil", true)
                .failureUrl("/login.html?error=connection")
                .usernameParameter("login")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/accueil");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}