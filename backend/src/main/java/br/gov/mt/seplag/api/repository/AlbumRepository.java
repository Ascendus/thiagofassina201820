package br.gov.mt.seplag.api.repository;

import br.gov.mt.seplag.api.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    Page<Album> findByArtistasId(Long artistaId, Pageable pageable);

}