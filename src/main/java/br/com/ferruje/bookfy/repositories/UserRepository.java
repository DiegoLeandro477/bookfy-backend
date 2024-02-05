package br.com.ferruje.bookfy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ferruje.bookfy.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
  public Optional<User> findByName(String name);
}
