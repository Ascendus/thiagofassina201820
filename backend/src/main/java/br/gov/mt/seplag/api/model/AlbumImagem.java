package br.gov.mt.seplag.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "album_imagens")
public class AlbumImagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @Column(name = "object_key", nullable = false, length = 500)
    private String objectKey;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

}
