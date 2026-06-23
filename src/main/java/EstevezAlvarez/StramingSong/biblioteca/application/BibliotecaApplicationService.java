package EstevezAlvarez.StramingSong.biblioteca.application;

import EstevezAlvarez.StramingSong.biblioteca.acl.CatalogoPort;
import EstevezAlvarez.StramingSong.biblioteca.domain.model.Favoritos;
import EstevezAlvarez.StramingSong.biblioteca.domain.model.Playlist;
import EstevezAlvarez.StramingSong.biblioteca.domain.model.PlaylistId;
import EstevezAlvarez.StramingSong.biblioteca.domain.model.ReferenciaMusica;
import EstevezAlvarez.StramingSong.biblioteca.domain.repository.FavoritosRepository;
import EstevezAlvarez.StramingSong.biblioteca.domain.repository.PlaylistRepository;
import EstevezAlvarez.StramingSong.shared.domain.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BibliotecaApplicationService {

    private final PlaylistRepository playlistRepository;
    private final FavoritosRepository favoritosRepository;
    private final CatalogoPort catalogoPort;

    @Transactional
    public Playlist criarPlaylist(String usuarioId, String nome) {
        Playlist playlist = Playlist.criar(usuarioId, nome);
        return playlistRepository.salvar(playlist);
    }

    @Transactional
    public Playlist adicionarMusicaNaPlaylist(String playlistId, String musicaId) {
        Playlist playlist = buscarPlaylistOuLancarExcecao(playlistId);
        ReferenciaMusica referencia = catalogoPort.buscarReferenciaMusica(musicaId)
                .orElseThrow(() -> new DomainException("Música não encontrada no catálogo: " + musicaId));
        playlist.adicionarMusica(referencia);
        return playlistRepository.salvar(playlist);
    }

    @Transactional
    public Playlist removerMusicaDaPlaylist(String playlistId, String musicaId) {
        Playlist playlist = buscarPlaylistOuLancarExcecao(playlistId);
        playlist.removerMusica(musicaId);
        return playlistRepository.salvar(playlist);
    }

    @Transactional(readOnly = true)
    public List<Playlist> listarPlaylistsDoUsuario(String usuarioId) {
        return playlistRepository.buscarPorUsuario(UUID.fromString(usuarioId));
    }

    @Transactional
    public Favoritos favoritarMusica(String usuarioId, String musicaId) {
        Favoritos favoritos = obterOuCriarFavoritos(usuarioId);
        ReferenciaMusica referencia = catalogoPort.buscarReferenciaMusica(musicaId)
                .orElseThrow(() -> new DomainException("Música não encontrada no catálogo: " + musicaId));
        favoritos.favoritarMusica(referencia);
        return favoritosRepository.salvar(favoritos);
    }

    @Transactional
    public Favoritos desfavoritarMusica(String usuarioId, String musicaId) {
        Favoritos favoritos = obterFavoritosOuLancarExcecao(usuarioId);
        favoritos.desfavoritarMusica(musicaId);
        return favoritosRepository.salvar(favoritos);
    }

    @Transactional(readOnly = true)
    public Favoritos buscarFavoritos(String usuarioId) {
        return obterFavoritosOuLancarExcecao(usuarioId);
    }

    private Playlist buscarPlaylistOuLancarExcecao(String playlistId) {
        return playlistRepository.buscarPorId(PlaylistId.de(playlistId))
                .orElseThrow(() -> new DomainException("Playlist não encontrada: " + playlistId));
    }

    private Favoritos obterOuCriarFavoritos(String usuarioId) {
        return favoritosRepository.buscarPorUsuario(UUID.fromString(usuarioId))
                .orElseGet(() -> Favoritos.criarParaUsuario(usuarioId));
    }

    private Favoritos obterFavoritosOuLancarExcecao(String usuarioId) {
        return favoritosRepository.buscarPorUsuario(UUID.fromString(usuarioId))
                .orElseThrow(() -> new DomainException("Favoritos não encontrados para o usuário: " + usuarioId));
    }
}
