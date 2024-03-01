package br.com.ferruje.bookfy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ferruje.bookfy.entities.dtos.UserDTO;
import br.com.ferruje.bookfy.entities.user.User;
import br.com.ferruje.bookfy.repositories.UserRepository;
import br.com.ferruje.bookfy.services.authorization.AuthorizationUserService;
import jakarta.transaction.Transactional;

@Service
public class UserService {
  
  @Autowired
  private UserRepository repository;
  @Autowired
  AuthorizationUserService authorizationUserService;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(Long id) throws Exception {
    @SuppressWarnings("null")
    Optional<User> userOp = repository.findById(id);
    if (userOp.isPresent())
      return userOp.get(); 
    throw new Exception("Usuário não encontrado");
  }

  @Transactional
  public User create(UserDTO entity) throws Exception{
    if (authorizationUserService.loadUserByUsername(entity.email()) != null) return ResponseEntity.badRequest().build();
    
    String encryptedPassword = new BCryptPasswordEncoder().encode(entity.password());

    Optional<User> userOp = repository.findByName(entity.name());
    if (userOp.isPresent()) {
      throw new Exception("nome de usuário já existente");
    }

    User user = new User();
    user.setName(entity.name());
    user.setEmail(entity.email());
    user.setPassword(encryptedPassword);
    user.setRole(entity.role());
    return repository.save(user);
  }
}
