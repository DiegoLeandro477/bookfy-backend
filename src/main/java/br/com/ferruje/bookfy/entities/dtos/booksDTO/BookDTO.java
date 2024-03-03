package br.com.ferruje.bookfy.entities.dtos.booksDTO;

import java.util.List;

public record BookDTO(String name, String synopsis, Integer volume, Long author_id, List<String> genres) { }
