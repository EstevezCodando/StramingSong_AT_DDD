package EstevezAlvarez.StramingSong.biblioteca.acl;

import EstevezAlvarez.StramingSong.biblioteca.domain.model.ReferenciaMusica;
import EstevezAlvarez.StramingSong.catalogo.domain.model.Musica;
import EstevezAlvarez.StramingSong.catalogo.domain.model.MusicaId;
import EstevezAlvarez.StramingSong.catalogo.domain.repository.MusicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adapter do ACL — traduz o modelo do Catálogo para o modelo da Biblioteca.
 * Protege a Biblioteca de mudanças no contexto Catálogo (Anticorruption Layer).
 */
@Component
@RequiredArgsConstructor
public class CatalogoAdapter implements CatalogoPort {

    private final MusicaRepository musicaRepository;

    @Override
    public Optional<ReferenciaMusica> buscarReferenciaMusica(String musicaId) {
        return musicaRepository.buscarPorId(MusicaId.de(musicaId))
                .map(this::traduzirParaReferencia);
    }

    private ReferenciaMusica traduzirParaReferencia(Musica musica) {
        return ReferenciaMusica.de(musica.id().toString(), musica.getTitulo(), musica.getArtista());
    }
}
