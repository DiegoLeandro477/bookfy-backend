package br.com.ferruje.bookfy.services.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ferruje.bookfy.repositories.UserRepository;

@Service
public class AuthorizationUserService implements UserDetailsService{

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email);
  }
  
}
