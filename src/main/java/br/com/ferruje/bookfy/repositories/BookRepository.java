package br.com.ferruje.bookfy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ferruje.bookfy.entities.Book;
import br.com.ferruje.bookfy.entities.User;

import java.util.Optional;



public interface BookRepository extends JpaRepository<Book, Long>{
  Optional<Book> findByNameAndUser(String name, User user);    
}
