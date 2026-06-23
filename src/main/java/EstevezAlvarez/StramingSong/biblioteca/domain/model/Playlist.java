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
@Table(name = "playlists")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playlist extends AggregateRoot {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "usuario_id", nullable = false, columnDefinition = "uuid")
    @Getter
    private UUID usuarioId;

    @Column(name = "nome", nullable = false)
    @Getter
    private String nome;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "playlist_musicas", joinColumns = @JoinColumn(name = "playlist_id"))
    private List<ReferenciaMusica> musicas = new ArrayList<>();

    private Playlist(PlaylistId id, UUID usuarioId, String nome) {
        this.id = id.valor();
        this.usuarioId = usuarioId;
        this.nome = nome;
    }

    public static Playlist criar(String usuarioId, String nome) {
        validarNome(nome);
        return new Playlist(PlaylistId.novo(), UUID.fromString(usuarioId), nome.trim());
    }

    public void adicionarMusica(ReferenciaMusica musica) {
        if (musicas.contains(musica)) {
            throw new DomainException("Música já está na playlist: " + musica.titulo());
        }
        musicas.add(musica);
    }

    public void removerMusica(String musicaId) {
        boolean removida = musicas.removeIf(m -> m.musicaId().equals(musicaId));
        if (!removida) {
            throw new DomainException("Música não encontrada na playlist: " + musicaId);
        }
    }

    public void renomear(String novoNome) {
        validarNome(novoNome);
        this.nome = novoNome.trim();
    }

    public PlaylistId id() {
        return PlaylistId.de(id.toString());
    }

    public List<ReferenciaMusica> musicas() {
        return Collections.unmodifiableList(musicas);
    }

    private static void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new DomainException("Nome da playlist é obrigatório");
        }
    }
}
