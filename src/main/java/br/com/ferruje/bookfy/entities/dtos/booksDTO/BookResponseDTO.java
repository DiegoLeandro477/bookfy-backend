package br.com.ferruje.bookfy.entities.dtos.booksDTO;

import java.util.List;

import java.util.Date;

public record BookResponseDTO(
  Long id,
  String synopsis,
  Integer volume,
  Long author_id,
  List<Long> participations,
  List<String> genres,
  Integer pages,
  Date creation_date,
  Date update_date,
  Date publication_date
) { }
