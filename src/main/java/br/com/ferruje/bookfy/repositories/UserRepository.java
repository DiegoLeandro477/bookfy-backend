package br.com.ferruje.bookfy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.ferruje.bookfy.entities.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
  public Optional<User> findByName(String name);
  // necessário para o spring security
  UserDetails findByEmail(String email);
}
