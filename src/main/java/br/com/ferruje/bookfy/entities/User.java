package br.com.ferruje.bookfy.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity             @Table( name="users" )  @Setter
@AllArgsConstructor @NoArgsConstructor      @Getter 
public class User {

  @Id @GeneratedValue( strategy=GenerationType.IDENTITY )
  private Long id;

  private String name, email, password;

  @OneToMany( mappedBy = "user" )
  @JsonIgnore
  private List<Book> books;
  
  @Nonnull private Date date_create;
  @Nonnull private Date date_update;
  @PrePersist protected void onCreate() {
    this.date_create = new Date(System.currentTimeMillis());
    this.date_update = date_create;
  }

}
