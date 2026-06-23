package EstevezAlvarez.StramingSong.catalogo.infra;

import EstevezAlvarez.StramingSong.catalogo.domain.model.Genero;
import EstevezAlvarez.StramingSong.catalogo.domain.model.Musica;
import EstevezAlvarez.StramingSong.catalogo.domain.model.MusicaId;
import EstevezAlvarez.StramingSong.catalogo.domain.repository.MusicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MusicaRepositoryImpl implements MusicaRepository {

    private final MusicaJpaRepository jpa;

    @Override
    public Musica salvar(Musica musica) {
        return jpa.save(musica);
    }

    @Override
    public Optional<Musica> buscarPorId(MusicaId id) {
        return jpa.findById(id.valor());
    }

    @Override
    public List<Musica> buscarPorArtista(String artista) {
        return jpa.findByArtista(artista);
    }

    @Override
    public List<Musica> buscarPorGenero(Genero genero) {
        return jpa.findByGenero(genero);
    }

    @Override
    public List<Musica> listarTodas() {
        return jpa.findAll();
    }
}
