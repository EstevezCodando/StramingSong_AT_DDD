package EstevezAlvarez.StramingSong.catalogo.domain.repository;

import EstevezAlvarez.StramingSong.catalogo.domain.model.Genero;
import EstevezAlvarez.StramingSong.catalogo.domain.model.Musica;
import EstevezAlvarez.StramingSong.catalogo.domain.model.MusicaId;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository {
    Musica salvar(Musica musica);
    Optional<Musica> buscarPorId(MusicaId id);
    List<Musica> buscarPorArtista(String artista);
    List<Musica> buscarPorGenero(Genero genero);
    List<Musica> listarTodas();
}
