package EstevezAlvarez.StramingSong.biblioteca.infra;

import EstevezAlvarez.StramingSong.biblioteca.domain.model.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface FavoritosJpaRepository extends JpaRepository<Favoritos, UUID> {
    Optional<Favoritos> findByUsuarioId(UUID usuarioId);
}
