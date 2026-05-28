package br.gov.mt.seplag.api.dto;

public record AlbumImagemResponse(
        Long id,
        Long albumId,
        String objectKey
) {}