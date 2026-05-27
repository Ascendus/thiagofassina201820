package br.gov.mt.seplag.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AlbumRequest(

        @NotBlank(message = "Título é obrigatório")
        @Size(max = 300, message = "Título deve ter no máximo 300 caracteres")
        String titulo,

        @NotNull(message = "Artista é obrigatório")
        Long artistaId

) {}