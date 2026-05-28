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

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;
    private final MinioService minioService;

    public AlbumService(AlbumRepository albumRepository,
                        ArtistaRepository artistaRepository,
                        MinioService minioService) {
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
        this.minioService = minioService;
    }

    public Page<AlbumResponse> listarPorArtista(Long artistaId, Pageable pageable) {
        return albumRepository
                .findByArtistasId(artistaId, pageable)
                .map(this::toResponse);
    }

    public AlbumResponse criar(AlbumRequest request) {
        Artista artista = artistaRepository.findById(request.artistaId())
                .orElseThrow(() -> new ResourceNotFoundException("Artista não encontrado"));

        Album album = new Album();
        album.setTitulo(request.titulo());
        Album albumSalvo = albumRepository.save(album);

        artista.getAlbuns().add(albumSalvo);
        artistaRepository.save(artista);

        return toResponse(albumSalvo);
    }

    private AlbumResponse toResponse(Album album) {
        List<String> urls = album.getImagens() == null
                ? List.of()
                : album.getImagens().stream()
                .map(imagem -> minioService.gerarPresignedUrl(imagem.getObjectKey()))
                .toList();

        return new AlbumResponse(
                album.getId(),
                album.getTitulo(),
                urls);
    }
}