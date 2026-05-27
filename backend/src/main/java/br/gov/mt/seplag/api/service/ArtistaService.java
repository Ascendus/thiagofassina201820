package br.gov.mt.seplag.api.service;

import br.gov.mt.seplag.api.dto.ArtistaRequest;
import br.gov.mt.seplag.api.dto.ArtistaResponse;
import br.gov.mt.seplag.api.model.Artista;
import br.gov.mt.seplag.api.repository.ArtistaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    private ArtistaResponse toResponse(Artista artista) {
        long total = artista.getAlbuns() == null ? 0 : artista.getAlbuns().size();
        return new ArtistaResponse(
                artista.getId(),
                artista.getNome(),
                artista.getTipo(),
                total);
    }

    public Page<ArtistaResponse> listar(String nome, Pageable pageable) {
        return artistaRepository
                .findByNomeContainingIgnoreCase(nome, pageable)
                .map(this::toResponse);

    }

    public ArtistaResponse buscarPorId(Long id) {
        Artista artista = artistaRepository.findById(id).orElseThrow(() -> new RuntimeException("Artista não encontrado."));
        return toResponse(artista);

    }

    public ArtistaResponse criar(ArtistaRequest request) {
        Artista artista = new Artista();
        artista.setNome(request.nome());
        artista.setTipo(request.tipo());
        return toResponse(artistaRepository.save(artista));
    }

    public ArtistaResponse editar(Long id, ArtistaRequest request) {
        Artista artista = artistaRepository.findById(id).orElseThrow(() -> new RuntimeException("Artista não encontrado."));
        artista.setNome(request.nome());
        artista.setTipo(request.tipo());
        return  toResponse(artistaRepository.save(artista));
    }


}
