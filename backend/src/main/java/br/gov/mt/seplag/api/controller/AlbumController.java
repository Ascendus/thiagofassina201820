package br.gov.mt.seplag.api.controller;

import br.gov.mt.seplag.api.dto.AlbumRequest;
import br.gov.mt.seplag.api.dto.AlbumResponse;
import br.gov.mt.seplag.api.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/albuns")
@SecurityRequirement(name = "bearerAuth")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/artista/{artistaId}")
    @Operation(summary = "Lista álbuns de um artista", parameters = {
            @Parameter(name = "page", description = "Número da página", example = "0"),
            @Parameter(name = "size", description = "Tamanho da página", example = "10"),
            @Parameter(name = "sort", description = "Ordenação ex: titulo,asc ou titulo,desc", example = "titulo,asc")
    })
    public ResponseEntity<Page<AlbumResponse>> listarPorArtista(
            @PathVariable Long artistaId,
            @PageableDefault(size = 10, sort = "titulo", direction = Sort.Direction.ASC)
            @Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(albumService.listarPorArtista(artistaId, pageable));
    }

    @PostMapping
    public ResponseEntity<AlbumResponse> criar(@RequestBody @Valid AlbumRequest request) {
        return ResponseEntity.status(201).body(albumService.criar(request));
    }


}
