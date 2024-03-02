package br.com.ferruje.bookfy.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.ferruje.bookfy.entities.user.User;

@Service
public class TokenService {
  
  @Value("${api.security.token.secret}")
  private String secret;

  public String generatedToken(User user){
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token = JWT.create()
        .withIssuer("auth-api")
        .withSubject(user.getEmail())
        .withExpiresAt(genExpirationDate())
        .sign(algorithm);
        return token;
    } catch (JWTCreationException e) {
      throw new RuntimeException("Error while generation Token", e);
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
        .withIssuer("auth-api")
        .build()
        .verify(token)
        .getSubject();
        
    } catch (JWTVerificationException e) {
      return "";
    }
  }

  public Instant genExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("+03:00"));
  }

}
