package br.gov.mt.seplag.api.dto;

public record ArtistaResponse(
        Long id,
        String nome,
        String tipo,
        long totalAlbuns
) {
}
