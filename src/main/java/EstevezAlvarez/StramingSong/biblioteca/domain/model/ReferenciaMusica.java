package EstevezAlvarez.StramingSong.biblioteca.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.DomainException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;

/**
 * Value Object que representa uma música dentro da Biblioteca.
 * Isola o contexto Biblioteca do modelo interno do Catálogo (ACL).
 */
@Embeddable
public class ReferenciaMusica {

    @Column(name = "musica_id")
    private UUID musicaId;

    @Column(name = "titulo_musica")
    private String titulo;

    @Column(name = "artista_musica")
    private String artista;

    protected ReferenciaMusica() {}

    private ReferenciaMusica(UUID musicaId, String titulo, String artista) {
        this.musicaId = musicaId;
        this.titulo = titulo;
        this.artista = artista;
    }

    public static ReferenciaMusica de(String musicaId, String titulo, String artista) {
        Objects.requireNonNull(musicaId, "ID da música não pode ser nulo");
        Objects.requireNonNull(titulo, "Título da música não pode ser nulo");
        Objects.requireNonNull(artista, "Artista não pode ser nulo");
        try {
            return new ReferenciaMusica(UUID.fromString(musicaId), titulo, artista);
        } catch (IllegalArgumentException e) {
            throw new DomainException("ID de música inválido: " + musicaId);
        }
    }

    public String musicaId() {
        return musicaId.toString();
    }

    public String titulo() {
        return titulo;
    }

    public String artista() {
        return artista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReferenciaMusica that)) return false;
        return Objects.equals(musicaId, that.musicaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(musicaId);
    }
}
