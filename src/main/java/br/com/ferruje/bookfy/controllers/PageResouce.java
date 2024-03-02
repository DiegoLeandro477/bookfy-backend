package br.com.ferruje.bookfy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferruje.bookfy.entities.Page;
import br.com.ferruje.bookfy.entities.dtos.PageDTO;
import br.com.ferruje.bookfy.services.PageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/pages")
public class PageResouce {  

  @Autowired
  public PageService service;
  
  @GetMapping
  public ResponseEntity<List<Page>> findAllByBook(@RequestParam Long book_id) throws Exception {
    return ResponseEntity.ok(service.findAllByBook(book_id));
  }

  @PostMapping
  public ResponseEntity<Page> create(@RequestBody PageDTO entity) throws Exception{
    return ResponseEntity.ok(service.create(entity));
  }
  
}
