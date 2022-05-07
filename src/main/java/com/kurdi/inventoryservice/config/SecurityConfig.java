package com.kurdi.inventoryservice.config;



import com.kurdi.inventoryservice.auth.filters.JwtTokenVerifierFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtTokenVerifierFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .mvcMatchers("/user")
                .permitAll()
                .mvcMatchers("/admin")
                .hasAnyAuthority("admin")
                .mvcMatchers(HttpMethod.GET,"/inventory/**")
                .permitAll()
                .mvcMatchers("/inventory/**")
                .hasAnyAuthority("supplier")

                .mvcMatchers("/categories/**")
                .hasAnyAuthority("admin")
                .mvcMatchers(HttpMethod.GET,"/categories/**")
                .permitAll();


    }



}