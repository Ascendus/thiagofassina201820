package br.gov.mt.seplag.api.service;

import br.gov.mt.seplag.api.dto.AlbumImagemResponse;
import br.gov.mt.seplag.api.exception.ResourceNotFoundException;
import br.gov.mt.seplag.api.model.Album;
import br.gov.mt.seplag.api.model.AlbumImagem;
import br.gov.mt.seplag.api.repository.AlbumImagemRepository;
import br.gov.mt.seplag.api.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumImagemService {

    private final AlbumRepository albumRepository;
    private final AlbumImagemRepository albumImagemRepository;
    private final MinioService minioService;

    public AlbumImagemService(AlbumRepository albumRepository,
                              AlbumImagemRepository albumImagemRepository,
                              MinioService minioService) {
        this.albumRepository = albumRepository;
        this.albumImagemRepository = albumImagemRepository;
        this.minioService = minioService;
    }

    public List<AlbumImagemResponse> upload(Long albumId, List<MultipartFile> arquivos) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResourceNotFoundException("Álbum não encontrado: " + albumId));

        List<AlbumImagemResponse> imagens = new ArrayList<>();

        for (MultipartFile arquivo : arquivos) {
            String objectKey = minioService.upload(arquivo);

            AlbumImagem imagem = new AlbumImagem();
            imagem.setAlbum(album);
            imagem.setObjectKey(objectKey);

            AlbumImagem salva = albumImagemRepository.save(imagem);
            imagens.add(new AlbumImagemResponse(salva.getId(), albumId, salva.getObjectKey()));
        }

        return imagens;
    }
}