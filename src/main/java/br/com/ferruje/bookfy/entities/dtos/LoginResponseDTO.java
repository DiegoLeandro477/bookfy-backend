package br.com.ferruje.bookfy.entities.dtos;

import br.com.ferruje.bookfy.entities.user.User;

public record LoginResponseDTO(String token, User user) {
  
}
