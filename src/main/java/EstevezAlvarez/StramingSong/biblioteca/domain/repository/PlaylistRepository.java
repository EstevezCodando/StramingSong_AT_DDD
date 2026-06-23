package EstevezAlvarez.StramingSong.biblioteca.domain.repository;

import EstevezAlvarez.StramingSong.biblioteca.domain.model.Playlist;
import EstevezAlvarez.StramingSong.biblioteca.domain.model.PlaylistId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaylistRepository {
    Playlist salvar(Playlist playlist);
    Optional<Playlist> buscarPorId(PlaylistId id);
    List<Playlist> buscarPorUsuario(UUID usuarioId);
}
