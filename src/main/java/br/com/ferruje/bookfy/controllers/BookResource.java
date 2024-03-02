package br.com.ferruje.bookfy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferruje.bookfy.entities.Book;
import br.com.ferruje.bookfy.entities.dtos.BookDTO;
import br.com.ferruje.bookfy.services.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/books")
public class BookResource {
  
  @Autowired
  private BookService service;

  @PostMapping
  public ResponseEntity<Book> createBook(@RequestBody BookDTO entity) throws Exception { 
    return ResponseEntity.ok(service.create(entity));
  }

  @GetMapping
  public ResponseEntity<List<Book>> findAll() {
      return ResponseEntity.ok(service.findAll());
  }
  
  
}
