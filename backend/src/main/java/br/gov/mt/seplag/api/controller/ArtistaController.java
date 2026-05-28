package br.gov.mt.seplag.api.controller;

import br.gov.mt.seplag.api.dto.ArtistaRequest;
import br.gov.mt.seplag.api.dto.ArtistaResponse;
import br.gov.mt.seplag.api.service.ArtistaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    @Operation(summary = "Lista artistas", parameters = {
            @Parameter(name = "page", description = "Número da página", example = "0"),
            @Parameter(name = "size", description = "Tamanho da página", example = "10"),
            @Parameter(name = "sort", description = "Ordenação ex: nome,asc ou nome,desc", example = "nome,asc")
    })
    public ResponseEntity<Page<ArtistaResponse>> listar(
            @RequestParam(defaultValue = "") String nome,
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC)
            @Parameter(hidden = true) Pageable pageable) {
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
