package br.com.ferruje.bookfy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferruje.bookfy.entities.dtos.AuthenticationDTO;
import br.com.ferruje.bookfy.entities.dtos.RegisterDTO;
import br.com.ferruje.bookfy.entities.user.User;
import br.com.ferruje.bookfy.services.UserService;
import br.com.ferruje.bookfy.services.authorization.AuthorizationUserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationResource {

  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  AuthorizationUserService authorizationUserService;
  
  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthenticationDTO entity) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(entity.email(), entity.password());
    var auth = (User) authenticationManager.authenticate(usernamePassword).getPrincipal();

    return ResponseEntity.ok().build();
  }
  
  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid RegisterDTO entity) {
      if (authorizationUserService.loadUserByUsername(entity.email()) != null) return ResponseEntity.badRequest().build();
      
      String encryptedPassword = new BCryptPasswordEncoder().encode(entity.password());
      return null;
  }
  

}
