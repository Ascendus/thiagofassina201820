package br.gov.mt.seplag.api.dto;

import java.util.List;

public record AlbumResponse(

        Long id,
        String titulo,
        List<String> imagensUrl

) {}