package EstevezAlvarez.StramingSong.catalogo.application;

import EstevezAlvarez.StramingSong.catalogo.domain.model.Genero;
import EstevezAlvarez.StramingSong.catalogo.domain.model.Musica;
import EstevezAlvarez.StramingSong.catalogo.domain.model.MusicaId;
import EstevezAlvarez.StramingSong.catalogo.domain.repository.MusicaRepository;
import EstevezAlvarez.StramingSong.shared.domain.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogoApplicationService {

    private final MusicaRepository musicaRepository;

    @Transactional
    public Musica catalogarMusica(String titulo, String artista, String album, long duracaoSegundos, Genero genero) {
        Musica musica = Musica.catalogar(titulo, artista, album, Duration.ofSeconds(duracaoSegundos), genero);
        return musicaRepository.salvar(musica);
    }

    @Transactional(readOnly = true)
    public Musica buscarPorId(String id) {
        return musicaRepository.buscarPorId(MusicaId.de(id))
                .orElseThrow(() -> new DomainException("Música não encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public List<Musica> listarTodas() {
        return musicaRepository.listarTodas();
    }

    @Transactional(readOnly = true)
    public List<Musica> buscarPorArtista(String artista) {
        return musicaRepository.buscarPorArtista(artista);
    }
}
