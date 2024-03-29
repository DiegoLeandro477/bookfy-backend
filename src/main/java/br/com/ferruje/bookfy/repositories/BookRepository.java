package br.com.ferruje.bookfy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ferruje.bookfy.entities.Book;
import br.com.ferruje.bookfy.entities.user.User;

import java.util.Optional;




public interface BookRepository extends JpaRepository<Book, Long>{
  Optional<Book> findByNameAndAuthor(String name, User author);    
}
