package br.com.ferruje.bookfy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ferruje.bookfy.entities.Book;
import br.com.ferruje.bookfy.entities.dtos.booksDTO.BookDTO;
import br.com.ferruje.bookfy.entities.dtos.booksDTO.BookResponseDTO;
import br.com.ferruje.bookfy.entities.user.User;
import br.com.ferruje.bookfy.repositories.BookRepository;
import jakarta.transaction.Transactional;

@Service
public class BookService {
  
  @Autowired
  private BookRepository repository;

  @Autowired
  private UserService userService;

  public List<BookResponseDTO> findAll(){
    List<Book> list = repository.findAll();
    List<BookResponseDTO> list_books = new ArrayList<>();
    for (Book book : list) {
      BookResponseDTO response = transformerResponse(book);
      list_books.add(response);
    }
    return list_books;
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
    User user = userService.findById(entity.author_id());
    Optional<Book> bookOp = repository.findByNameAndUser(entity.name(), user);
    if (bookOp.isPresent()){
      throw new Exception("vc já utilizou esse nome");
    }
    Book book = new Book();
    book.setName(entity.name());
    book.setSynopsis(entity.synopsis());
    book.setVolume(entity.volume());
    book.setAuthor(user);
    book.setGenres(entity.genres());
    return repository.save(book);
  }

  public BookResponseDTO transformerResponse(Book book){
    return new BookResponseDTO(
      book.getId(),
      book.getSynopsis(),
      book.getVolume(),
      book.getAuthor().getId(),
      book.getParticipations().stream().map(user -> user.getId()).collect(Collectors.toList()),
      book.getGenres(),
      book.getPages().size(),
      book.getCreation_date(),
      book.getUpdate_date(),
      book.getPublication_date()    
    );
  }
}
