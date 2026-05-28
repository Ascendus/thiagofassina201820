package br.gov.mt.seplag.api.controller;

import br.gov.mt.seplag.api.dto.AlbumImagemResponse;
import br.gov.mt.seplag.api.service.AlbumImagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/albuns")
@SecurityRequirement(name = "bearerAuth")
public class AlbumImagemController {

    private final AlbumImagemService albumImagemService;

    public AlbumImagemController(AlbumImagemService albumImagemService) {
        this.albumImagemService = albumImagemService;
    }

    @Operation(summary = "Faz upload de uma ou mais capas para um álbum")
    @PostMapping(value = "/{id}/imagens", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<AlbumImagemResponse>> upload(
            @PathVariable Long id,
            @RequestPart("arquivos") List<MultipartFile> arquivos) {

        return ResponseEntity.status(201).body(albumImagemService.upload(id, arquivos));
    }
}