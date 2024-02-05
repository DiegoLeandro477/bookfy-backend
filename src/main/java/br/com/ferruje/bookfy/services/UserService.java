package br.com.ferruje.bookfy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ferruje.bookfy.entities.User;
import br.com.ferruje.bookfy.entities.dtos.UserDTO;
import br.com.ferruje.bookfy.repositories.UserRepository;

@Service
public class UserService {
  
  @Autowired
  private UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User create(UserDTO entity) throws Exception{
    Optional<User> userOp = repository.findByName(entity.name());
    if (userOp.isPresent()) {
      throw new Exception("nome de usuário já existente");
    }
    User user = new User();
    user.setName(entity.name());
    user.setEmail(entity.email());
    user.setPassword(entity.password());
    return repository.save(user);
  }
}
