package EstevezAlvarez.StramingSong.biblioteca.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.AggregateRoot;
import EstevezAlvarez.StramingSong.shared.domain.DomainException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "favoritos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favoritos extends AggregateRoot {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "usuario_id", nullable = false, unique = true, columnDefinition = "uuid")
    @Getter
    private UUID usuarioId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "favoritos_musicas", joinColumns = @JoinColumn(name = "favoritos_id"))
    private List<ReferenciaMusica> musicasFavoritas = new ArrayList<>();

    private Favoritos(UUID id, UUID usuarioId) {
        this.id = id;
        this.usuarioId = usuarioId;
    }

    public static Favoritos criarParaUsuario(String usuarioId) {
        return new Favoritos(UUID.randomUUID(), UUID.fromString(usuarioId));
    }

    public void favoritarMusica(ReferenciaMusica musica) {
        if (musicasFavoritas.contains(musica)) {
            throw new DomainException("Música já está nos favoritos: " + musica.titulo());
        }
        musicasFavoritas.add(musica);
    }

    public void desfavoritarMusica(String musicaId) {
        boolean removida = musicasFavoritas.removeIf(m -> m.musicaId().equals(musicaId));
        if (!removida) {
            throw new DomainException("Música não encontrada nos favoritos: " + musicaId);
        }
    }

    public List<ReferenciaMusica> musicasFavoritas() {
        return Collections.unmodifiableList(musicasFavoritas);
    }

    public UUID id() {
        return id;
    }
}
