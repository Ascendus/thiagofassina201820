package br.gov.mt.seplag.api.controller;

import br.gov.mt.seplag.api.dto.ArtistaRequest;
import br.gov.mt.seplag.api.dto.ArtistaResponse;
import br.gov.mt.seplag.api.service.ArtistaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping
    public ResponseEntity<Page<ArtistaResponse>> listar(@RequestParam(defaultValue = "") String nome, Pageable pageable) {
        return ResponseEntity.ok(artistaService.listar(nome, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(artistaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ArtistaResponse> criar(@RequestBody @Valid ArtistaRequest request) {
        return ResponseEntity.status(201).body(artistaService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaResponse> editar(
            @PathVariable Long id,
            @RequestBody @Valid ArtistaRequest request) {
        return ResponseEntity.ok(artistaService.editar(id, request));
    }


}
