package EstevezAlvarez.StramingSong.biblioteca.api;

import EstevezAlvarez.StramingSong.biblioteca.application.BibliotecaApplicationService;
import EstevezAlvarez.StramingSong.biblioteca.domain.model.Favoritos;
import EstevezAlvarez.StramingSong.biblioteca.domain.model.Playlist;
import EstevezAlvarez.StramingSong.biblioteca.domain.model.ReferenciaMusica;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Biblioteca do Usuário", description = "Playlists e músicas favoritas")
public class BibliotecaController {

    private final BibliotecaApplicationService bibliotecaService;

    @PostMapping("/api/usuarios/{usuarioId}/playlists")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar playlist")
    public PlaylistResponse criarPlaylist(@PathVariable String usuarioId, @RequestBody @Valid CriarPlaylistRequest request) {
        Playlist playlist = bibliotecaService.criarPlaylist(usuarioId, request.nome());
        return PlaylistResponse.de(playlist);
    }

    @GetMapping("/api/usuarios/{usuarioId}/playlists")
    @Operation(summary = "Listar playlists do usuário")
    public List<PlaylistResponse> listarPlaylists(@PathVariable String usuarioId) {
        return bibliotecaService.listarPlaylistsDoUsuario(usuarioId).stream().map(PlaylistResponse::de).toList();
    }

    @PostMapping("/api/playlists/{playlistId}/musicas/{musicaId}")
    @Operation(summary = "Adicionar música à playlist")
    public PlaylistResponse adicionarMusica(@PathVariable String playlistId, @PathVariable String musicaId) {
        return PlaylistResponse.de(bibliotecaService.adicionarMusicaNaPlaylist(playlistId, musicaId));
    }

    @DeleteMapping("/api/playlists/{playlistId}/musicas/{musicaId}")
    @Operation(summary = "Remover música da playlist")
    public PlaylistResponse removerMusica(@PathVariable String playlistId, @PathVariable String musicaId) {
        return PlaylistResponse.de(bibliotecaService.removerMusicaDaPlaylist(playlistId, musicaId));
    }

    @PostMapping("/api/usuarios/{usuarioId}/favoritos/{musicaId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Favoritar música")
    public FavoritosResponse favoritarMusica(@PathVariable String usuarioId, @PathVariable String musicaId) {
        return FavoritosResponse.de(bibliotecaService.favoritarMusica(usuarioId, musicaId));
    }

    @DeleteMapping("/api/usuarios/{usuarioId}/favoritos/{musicaId}")
    @Operation(summary = "Desfavoritar música")
    public FavoritosResponse desfavoritarMusica(@PathVariable String usuarioId, @PathVariable String musicaId) {
        return FavoritosResponse.de(bibliotecaService.desfavoritarMusica(usuarioId, musicaId));
    }

    @GetMapping("/api/usuarios/{usuarioId}/favoritos")
    @Operation(summary = "Listar músicas favoritas do usuário")
    public FavoritosResponse buscarFavoritos(@PathVariable String usuarioId) {
        return FavoritosResponse.de(bibliotecaService.buscarFavoritos(usuarioId));
    }

    public record CriarPlaylistRequest(@jakarta.validation.constraints.NotBlank String nome) {}

    public record PlaylistResponse(String id, String nome, List<MusicaItemResponse> musicas) {
        public static PlaylistResponse de(Playlist p) {
            List<MusicaItemResponse> items = p.musicas().stream()
                    .map(m -> new MusicaItemResponse(m.musicaId(), m.titulo(), m.artista()))
                    .toList();
            return new PlaylistResponse(p.id().toString(), p.getNome(), items);
        }
    }

    public record FavoritosResponse(String usuarioId, List<MusicaItemResponse> favoritas) {
        public static FavoritosResponse de(Favoritos f) {
            List<MusicaItemResponse> items = f.musicasFavoritas().stream()
                    .map(m -> new MusicaItemResponse(m.musicaId(), m.titulo(), m.artista()))
                    .toList();
            return new FavoritosResponse(f.getUsuarioId().toString(), items);
        }
    }

    public record MusicaItemResponse(String musicaId, String titulo, String artista) {}
}
