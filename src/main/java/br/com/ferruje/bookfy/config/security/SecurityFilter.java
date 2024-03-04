package br.com.ferruje.bookfy.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.ferruje.bookfy.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

  @Autowired
  TokenService tokenService;
  @Autowired
  UserService userService;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain)
      throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
          var login = tokenService.validateToken(token);
          System.out.println("\n\n Email: " + login + "\n");
          UserDetails user = userService.loadUserByUsername(login);

          System.out.println("\n\n USER: " + user.getAuthorities());

          var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");


    if (authHeader == null)
      return null;
    
    return authHeader.replace("Bearer ", "");
  }
  
}
