package br.gov.mt.seplag.api.controller;

import br.gov.mt.seplag.api.dto.AlbumRequest;
import br.gov.mt.seplag.api.dto.AlbumResponse;
import br.gov.mt.seplag.api.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/albuns")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/artista/{artistaId}")
    public ResponseEntity<Page<AlbumResponse>> listarPorArtista(
            @PathVariable Long artistaId,
            Pageable pageable) {
        return ResponseEntity.ok(albumService.listarPorArtista(artistaId, pageable));
    }

    @PostMapping
    public ResponseEntity<AlbumResponse> criar(@RequestBody @Valid AlbumRequest request) {
        return ResponseEntity.status(201).body(albumService.criar(request));
    }


}
