package EstevezAlvarez.StramingSong.catalogo.infra;

import EstevezAlvarez.StramingSong.catalogo.domain.model.Genero;
import EstevezAlvarez.StramingSong.catalogo.domain.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface MusicaJpaRepository extends JpaRepository<Musica, UUID> {
    List<Musica> findByArtista(String artista);
    List<Musica> findByGenero(Genero genero);
}
