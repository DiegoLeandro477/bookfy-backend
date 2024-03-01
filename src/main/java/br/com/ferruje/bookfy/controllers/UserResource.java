package br.com.ferruje.bookfy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ferruje.bookfy.entities.dtos.UserDTO;
import br.com.ferruje.bookfy.entities.user.User;
import br.com.ferruje.bookfy.services.UserService;

@Controller
@RequestMapping("/api/users")
public class UserResource {
  
  @Autowired
  private UserService serviceUser;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody UserDTO entity) throws Exception {
    return ResponseEntity.ok(serviceUser.create(entity));
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllBooks() {
    return ResponseEntity.ok(serviceUser.findAll());
  }
  
}
