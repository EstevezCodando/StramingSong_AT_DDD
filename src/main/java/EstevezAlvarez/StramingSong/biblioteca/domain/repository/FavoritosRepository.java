package EstevezAlvarez.StramingSong.biblioteca.domain.repository;

import EstevezAlvarez.StramingSong.biblioteca.domain.model.Favoritos;

import java.util.Optional;
import java.util.UUID;

public interface FavoritosRepository {
    Favoritos salvar(Favoritos favoritos);
    Optional<Favoritos> buscarPorUsuario(UUID usuarioId);
}
