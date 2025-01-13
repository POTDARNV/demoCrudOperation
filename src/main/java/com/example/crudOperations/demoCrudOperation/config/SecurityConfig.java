package com.example.crudOperations.demoCrudOperation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    Logger logger1= LoggerFactory.getLogger(SecurityConfig.class);
@Autowired
    private UserDetailsService userDetailsService;



    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {


return security.csrf(Customizer->Customizer.disable())
        .authorizeRequests(request->request
                        .requestMatchers("/auth/signin","/auth/login","/auth/refresh_token").permitAll()
                .requestMatchers("/emp/addEmp").hasRole("USER")
                .requestMatchers("/emp/**","/address/**","/project/**","/dept/**","/auth/getAll").hasRole("ADMIN")
                .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults())
       .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        logger1.info("Testing:-"+provider.toString());
        return provider;
    }

}
