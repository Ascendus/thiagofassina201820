package br.gov.mt.seplag.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ArtistaRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 200, message = "Nome deve ter no máximo 200 caracteres")
        String nome,

        @NotBlank(message = "Tipo é obrigatório")
        @Size(max = 20, message = "Tipo deve ter no máximo 20 caracteres")
        String tipo

) {
}
