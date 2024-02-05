package br.com.ferruje.bookfy.entities;

import java.util.Date;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  
  @Nonnull private Date create_date_account;
  @PrePersist protected void onCreate() {
    this.create_date_account = new Date(System.currentTimeMillis());
  }

}
