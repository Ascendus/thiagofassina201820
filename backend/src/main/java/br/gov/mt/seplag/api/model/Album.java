package br.gov.mt.seplag.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "albuns")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String titulo;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "albuns")
    private List<Artista> artistas;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<AlbumImagem> imagens;

}
