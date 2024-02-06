package br.com.ferruje.bookfy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ferruje.bookfy.entities.Page;

public interface PageRepository extends JpaRepository<Page, Long>{
  
}
