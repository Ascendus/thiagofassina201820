package br.gov.mt.seplag.api.controller;

import br.gov.mt.seplag.api.model.Regional;
import br.gov.mt.seplag.api.repository.RegionalRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/regionais")
@SecurityRequirement(name = "bearerAuth")
public class RegionalController {

    private final RegionalRepository regionalRepository;

    public RegionalController(RegionalRepository regionalRepository) {
        this.regionalRepository = regionalRepository;
    }

    @GetMapping
    @Operation(summary = "Lista todas as regionais ativas")
    public ResponseEntity<List<Regional>> listar() {
        List<Regional> regionais = regionalRepository.findAll()
                .stream()
                .filter(Regional::getAtivo)
                .toList();
        return ResponseEntity.ok(regionais);
    }
}