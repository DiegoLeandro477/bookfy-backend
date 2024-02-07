package br.com.ferruje.bookfy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ferruje.bookfy.entities.Book;
import br.com.ferruje.bookfy.entities.Page;
import java.util.List;
import java.util.Optional;


public interface PageRepository extends JpaRepository<Page, Long>{

  Optional<List<Page>> findByBook(Book book);
  
}
