package com.example.demo.config;


import com.example.demo.util.CustomEncoder;
import com.example.demo.util.Md5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true/*,securedEnabled = true,prePostEnabled = true*/)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .httpBasic().and()
                .csrf().disable()
                .cors().disable()
                .headers().frameOptions().disable()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/api/accounts/**").permitAll()
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/cards/**").hasRole("USER")
                .antMatchers("/api/**").authenticated();

    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder);

        /*auth.inMemoryAuthentication()
                .withUser("user1").password("password1").roles("role1").and()
                .withUser("user2").password("password2").roles("role2").and()
                .withUser("admin").password("admin_password").roles("role1","admin").and()
                .passwordEncoder(CustomEncoder.getInstance());*/
    }



}
