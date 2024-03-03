package br.com.ferruje.bookfy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferruje.bookfy.entities.dtos.booksDTO.BookDTO;
import br.com.ferruje.bookfy.entities.dtos.booksDTO.BookResponseDTO;
import br.com.ferruje.bookfy.services.BookService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/books")
public class BookResource {
  
  @Autowired
  private BookService service;

  @PostMapping
  public ResponseEntity<BookResponseDTO> createBook(@RequestBody @Valid BookDTO entity) throws Exception { 
    service.create(entity);
    return ResponseEntity.ok(service.transformerResponse(service.create(entity)));
  }

  @GetMapping
  public ResponseEntity<List<BookResponseDTO>> findAll() {
      return ResponseEntity.ok(service.findAll());
  }
  
}
