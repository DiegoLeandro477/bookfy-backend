package br.com.ferruje.bookfy.entities;

import java.util.Date;
import java.util.List;

import br.com.ferruje.bookfy.entities.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity             @Table( name="books" )  @Setter
@AllArgsConstructor @NoArgsConstructor      @Getter
public class Book {
  @Id   @GeneratedValue( strategy=GenerationType.IDENTITY )
  private Long id;

  private String name, sinopse;
  private Integer vol;
  
  @ManyToOne
  private User user;

  private List<User> participations;
  private Date date_create, date_publication, date_update;
  
  @OneToMany( mappedBy="book" )
  private List<Page> pages;

  private List<String> Generos;

  private byte[] image;

  @PrePersist
  protected void onCreate() {
    this.date_create = new Date(System.currentTimeMillis());
    this.date_update = date_create;
  }
}
