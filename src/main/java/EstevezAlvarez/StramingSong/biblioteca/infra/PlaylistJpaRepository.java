package EstevezAlvarez.StramingSong.biblioteca.infra;

import EstevezAlvarez.StramingSong.biblioteca.domain.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface PlaylistJpaRepository extends JpaRepository<Playlist, UUID> {
    List<Playlist> findByUsuarioId(UUID usuarioId);
}
