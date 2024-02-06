package br.com.ferruje.bookfy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ferruje.bookfy.entities.Book;
import br.com.ferruje.bookfy.entities.User;
import br.com.ferruje.bookfy.entities.dtos.BookDTO;
import br.com.ferruje.bookfy.repositories.BookRepository;
import jakarta.transaction.Transactional;

@Service
public class BookService {
  
  @Autowired
  private BookRepository repository;

  @Autowired
  private UserService userService;

  public List<Book> findAll(){
    return repository.findAll();
  }

  public Book findById(Long id) throws Exception {
    Optional<Book> bookOp = repository.findById(id);
    if (bookOp.isPresent()){
      return bookOp.get();
    }
    throw new Exception("Livro inexistente");
  }

  @Transactional
  public Book create(BookDTO entity) throws Exception {
    User user = userService.findById(entity.userId());
    Optional<Book> bookOp = repository.findByNameAndUser(entity.name(), user);
    if (bookOp.isPresent()){
      throw new Exception("vc já utilizou esse nome");
    }
    Book book = new Book();
    book.setName(entity.name());
    book.setSinopse(entity.sinopse());
    book.setVol(entity.vol());
    book.setUser(user);
    return repository.save(book);
  }
}