package br.com.ferruje.bookfy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ferruje.bookfy.entities.Book;
import br.com.ferruje.bookfy.entities.Page;
import br.com.ferruje.bookfy.entities.dtos.PageDTO;
import br.com.ferruje.bookfy.repositories.PageRepository;
import jakarta.transaction.Transactional;

@Service
public class PageService {
  
  @Autowired
  private PageRepository repository;

  @Autowired
  private BookService bookService;

  public List<Page> findAll() {
    return repository.findAll();
  }

  @Transactional
  public Page create(PageDTO entity) throws Exception {
    Book book = bookService.findById(entity.bookId());
    Page page = new Page();
    page.setNumber(entity.number());
    page.setContent(entity.content());
    page.setBook(book);

    return repository.save(page);
  }

}
