package com.phonestoreweb.phonestore.config;

import com.phonestoreweb.phonestore.dto.request.IntrospectRequest;
import com.phonestoreweb.phonestore.service.IAuthenticationService;
import com.phonestoreweb.phonestore.service.IUserService;
import com.phonestoreweb.phonestore.service.implService.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private IAuthenticationService authService;

    @Autowired
    private IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            try {
                username = authService.extractUsername(token);

                if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    var introspectResponse = authService.introspect(IntrospectRequest.builder().token(token).build());
                    if(!introspectResponse.isValid()) throw new JwtException("Token Invalid or Expire");
                    else if (introspectResponse.isValid()) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                    }

                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        filterChain.doFilter(request,response);
    }
}
