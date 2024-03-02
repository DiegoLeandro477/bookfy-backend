package br.com.ferruje.bookfy.entities.user;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ferruje.bookfy.entities.Book;
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
public class User implements UserDetails {

  @Id @GeneratedValue( strategy=GenerationType.IDENTITY )
  private Long id;

  private String name, email, password;
  private UserRole role;

  @OneToMany( mappedBy = "user" )
  @JsonIgnore
  private List<Book> books;
  
  @Nonnull private Date date_create;
  @Nonnull private Date date_update;
  @PrePersist protected void onCreate() {
    this.date_create = new Date(System.currentTimeMillis());
    this.date_update = date_create;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }
  @Override
  public String getUsername() {
    return email;
  }
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  @Override
  public boolean isEnabled() {
    return true;
  }

}
