package br.com.ferruje.bookfy.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ferruje.bookfy.entities.Book;
import br.com.ferruje.bookfy.entities.dtos.BookDTO;
import br.com.ferruje.bookfy.entities.user.User;
import br.com.ferruje.bookfy.repositories.BookRepository;
import jakarta.transaction.Transactional;

@Service
public class BookService {
  
  @Autowired
  private BookRepository repository;

  @Autowired
  private UserService userService;

  public List<Book> findAll(){
    List<Book> list = repository.findAll();

    return list.stream()
      .map(book -> {
        book.getUser().setId(null);
        book.getUser().setPassword(null);
        return book;
      })
      .collect(Collectors.toList());
  }

  public Book findById(Long id) throws Exception {
    @SuppressWarnings("null")
    Optional<Book> bookOp = repository.findById(id);
    if (bookOp.isPresent()){
      return bookOp.get();
    }
    throw new Exception("Livro inexistente");
  }

  @Transactional
  public Book create(BookDTO entity) throws Exception {
    User user = userService.findById(entity.user_id());
    Optional<Book> bookOp = repository.findByNameAndUser(entity.name(), user);
    if (bookOp.isPresent()){
      throw new Exception("vc já utilizou esse nome");
    }
    Book book = new Book();
    book.setName(entity.name());
    book.setSinopse(entity.sinopse());
    book.setVol(entity.vol());
    book.setUser(user);
    book.setGeneros(entity.generos());
    return repository.save(book);
  }
}
