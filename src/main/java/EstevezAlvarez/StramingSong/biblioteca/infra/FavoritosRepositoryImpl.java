package EstevezAlvarez.StramingSong.biblioteca.infra;

import EstevezAlvarez.StramingSong.biblioteca.domain.model.Favoritos;
import EstevezAlvarez.StramingSong.biblioteca.domain.repository.FavoritosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FavoritosRepositoryImpl implements FavoritosRepository {

    private final FavoritosJpaRepository jpa;

    @Override
    public Favoritos salvar(Favoritos favoritos) {
        return jpa.save(favoritos);
    }

    @Override
    public Optional<Favoritos> buscarPorUsuario(UUID usuarioId) {
        return jpa.findByUsuarioId(usuarioId);
    }
}
