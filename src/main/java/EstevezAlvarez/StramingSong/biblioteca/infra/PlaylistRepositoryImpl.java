package EstevezAlvarez.StramingSong.biblioteca.infra;

import EstevezAlvarez.StramingSong.biblioteca.domain.model.Playlist;
import EstevezAlvarez.StramingSong.biblioteca.domain.model.PlaylistId;
import EstevezAlvarez.StramingSong.biblioteca.domain.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PlaylistRepositoryImpl implements PlaylistRepository {

    private final PlaylistJpaRepository jpa;

    @Override
    public Playlist salvar(Playlist playlist) {
        return jpa.save(playlist);
    }

    @Override
    public Optional<Playlist> buscarPorId(PlaylistId id) {
        return jpa.findById(id.valor());
    }

    @Override
    public List<Playlist> buscarPorUsuario(UUID usuarioId) {
        return jpa.findByUsuarioId(usuarioId);
    }
}
