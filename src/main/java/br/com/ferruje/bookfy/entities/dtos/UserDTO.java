package br.com.ferruje.bookfy.entities.dtos;

import br.com.ferruje.bookfy.entities.user.UserRole;

public record UserDTO(String name, String email, String password, UserRole role) {
  
}
