package br.gov.mt.seplag.api.dto;

import java.time.LocalDateTime;

public record AlbumNotificacaoEvent(
        String titulo,
        String nomeArtista,
        LocalDateTime momento
) {
}
