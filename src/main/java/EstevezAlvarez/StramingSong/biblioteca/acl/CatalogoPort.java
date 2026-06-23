package EstevezAlvarez.StramingSong.biblioteca.acl;

import EstevezAlvarez.StramingSong.biblioteca.domain.model.ReferenciaMusica;

import java.util.Optional;

/**
 * Port (interface) do ACL — define como a Biblioteca enxerga o Catálogo.
 * Traduz o modelo externo do Catálogo para o modelo interno da Biblioteca.
 */
public interface CatalogoPort {
    Optional<ReferenciaMusica> buscarReferenciaMusica(String musicaId);
}
