package br.com.ferruje.bookfy.entities.dtos.booksDTO;

import java.util.List;

public record BookDTO(String name, String sinopse, Integer vol, Long user_id, List<String> generos) { }
