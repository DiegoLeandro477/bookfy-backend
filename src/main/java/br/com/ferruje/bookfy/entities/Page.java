package br.com.ferruje.bookfy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity             @Table( name="pages" )  @Setter
@AllArgsConstructor @NoArgsConstructor      @Getter
public class Page {
  
  @Id   @GeneratedValue( strategy = GenerationType.IDENTITY )
  private Long id;

  private String content;
  private Integer number;

    @ManyToOne
    public Book book;
}
