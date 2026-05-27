package br.gov.mt.seplag.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false, length = 20)
    private String tipo;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @ManyToMany
    @JoinTable(
            name = "artista_album",
            joinColumns = @JoinColumn(name = artista_id),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private List<Album> albuns;

}
