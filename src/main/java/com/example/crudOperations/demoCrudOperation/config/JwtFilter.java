package com.example.crudOperations.demoCrudOperation.config;

import com.example.crudOperations.demoCrudOperation.controller.EmployeeController;
import com.example.crudOperations.demoCrudOperation.service.CustomUserDetailsService;
import com.example.crudOperations.demoCrudOperation.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ApplicationContext context;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Logger logger1= LoggerFactory.getLogger(JwtFilter.class);
        String header=request.getHeader("Authorization");
String  token=null;
String identifier=null;
String role=null;
if (header!=null&&header.startsWith("Bearer "))  {
      token=header.substring(7);
    identifier=jwtService.extractUsername(token);
    //role=jwtService.extractRole(token);
    //System.err.println(role);
        }
        /*if (token != null) {
            boolean isRefreshToken = jwtService.isRefreshToken(token);
            String requestPath = request.getRequestURI();

            if (isRefreshToken && !requestPath.equals("/auth/refresh_token")) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Refresh tokens are not allowed for this endpoint.");
                return;
            }
        }*/

if (identifier!=null && SecurityContextHolder.getContext().getAuthentication()==null){
    UserDetails userDetails=context.getBean(CustomUserDetailsService.class).loadUserByUsername(identifier);


if (jwtService.validateToken(token,userDetails)){
    UsernamePasswordAuthenticationToken token1=
            new UsernamePasswordAuthenticationToken
                    (userDetails,null,userDetails.getAuthorities());
    logger1.info("Roles:-"+userDetails.getAuthorities().toString());

    token1.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(token1);
}
}
filterChain.doFilter(request,response);
    }
}
