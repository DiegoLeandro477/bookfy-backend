package br.com.ferruje.bookfy.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ferruje.bookfy.config.security.TokenService;
import br.com.ferruje.bookfy.entities.dtos.AuthenticationDTO;
import br.com.ferruje.bookfy.entities.dtos.LoginResponseDTO;
import br.com.ferruje.bookfy.entities.dtos.UserDTO;
import br.com.ferruje.bookfy.entities.user.User;
import br.com.ferruje.bookfy.services.UserService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserResource {
  
  @Autowired    private UserService userService;
  @Autowired    AuthenticationManager authenticationManager;
  @Autowired    TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO entity) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(entity.email(), entity.password());
    var auth = (User) authenticationManager.authenticate(usernamePassword).getPrincipal();
    var token = tokenService.generatedToken(auth);
    auth.setPassword(null);
    return ResponseEntity.ok(new LoginResponseDTO(token, auth));
  }

  @GetMapping("/verify")
  public ResponseEntity<Boolean> verify(){
    return ResponseEntity.ok(true);
  }
  
  @PostMapping("/register")
  public ResponseEntity<Boolean> register(@RequestBody @Valid UserDTO data) throws Exception{
        if(userService.loadUserByUsername(data.email()) != null) return ResponseEntity.badRequest().build();
        userService.create(data);
        return ResponseEntity.ok(true);
  }

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
      return ResponseEntity.ok(userService.findAll());
  }
  
  
}
