package br.com.ferruje.bookfy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferruje.bookfy.config.security.TokenService;
import br.com.ferruje.bookfy.entities.dtos.AuthenticationDTO;
import br.com.ferruje.bookfy.entities.dtos.LoginResponseDTO;
import br.com.ferruje.bookfy.entities.dtos.RegisterDTO;
import br.com.ferruje.bookfy.entities.dtos.UserDTO;
import br.com.ferruje.bookfy.entities.user.User;
import br.com.ferruje.bookfy.services.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("auth")
public class AuthenticationResource {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  TokenService tokenService;

  @Autowired
  UserService userService;
  
  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO entity) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(entity.email(), entity.password());
    var auth = (User) authenticationManager.authenticate(usernamePassword).getPrincipal();

    var token = tokenService.generatedToken(auth);

    return ResponseEntity.ok(new LoginResponseDTO(token));
  }
  
  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid UserDTO data) throws Exception{
        if(userService.loadUserByUsername(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        userService.create(data);
        return ResponseEntity.ok().build();
    }
  

}
