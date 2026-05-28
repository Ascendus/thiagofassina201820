package br.gov.mt.seplag.api.service;

import br.gov.mt.seplag.api.dto.AlbumRequest;
import br.gov.mt.seplag.api.dto.AlbumResponse;
import br.gov.mt.seplag.api.model.Album;
import br.gov.mt.seplag.api.model.Artista;
import br.gov.mt.seplag.api.repository.AlbumRepository;
import br.gov.mt.seplag.api.repository.ArtistaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.gov.mt.seplag.api.exception.ResourceNotFoundException;

import java.util.Collections;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistaRepository artistaRepository) {
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
    }

    public Page<AlbumResponse> listarPorArtista(Long artistaId, Pageable pageable) {
        return albumRepository
                .findByArtistasId(artistaId, pageable)
                .map(this::toResponse);
    }

    public AlbumResponse criar(AlbumRequest request) {
        Artista artista = artistaRepository.findById(request.artistaId()).orElseThrow(() -> new ResourceNotFoundException("Artista não encontrado"));
        Album album = new Album();
        album.setTitulo(request.titulo());
        artista.getAlbuns().add(album);
        artistaRepository.save(artista);
        return toResponse(albumRepository.save(album));
    }

    private AlbumResponse toResponse(Album album) {
        return new AlbumResponse(
                album.getId(),
                album.getTitulo(),
                Collections.emptyList());
    }


}
