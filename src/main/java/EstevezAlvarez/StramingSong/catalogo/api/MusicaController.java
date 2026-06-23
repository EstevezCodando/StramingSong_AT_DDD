package EstevezAlvarez.StramingSong.catalogo.api;

import EstevezAlvarez.StramingSong.catalogo.application.CatalogoApplicationService;
import EstevezAlvarez.StramingSong.catalogo.domain.model.Genero;
import EstevezAlvarez.StramingSong.catalogo.domain.model.Musica;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/musicas")
@RequiredArgsConstructor
@Tag(name = "Catálogo Musical", description = "Gerenciamento do catálogo de músicas")
public class MusicaController {

    private final CatalogoApplicationService catalogoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Catalogar nova música")
    public MusicaResponse catalogar(@RequestBody @Valid CatalogarMusicaRequest request) {
        Musica musica = catalogoService.catalogarMusica(
                request.titulo(), request.artista(), request.album(),
                request.duracaoSegundos(), request.genero()
        );
        return MusicaResponse.de(musica);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar música por ID")
    public MusicaResponse buscarPorId(@PathVariable String id) {
        return MusicaResponse.de(catalogoService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todas as músicas")
    public List<MusicaResponse> listarTodas() {
        return catalogoService.listarTodas().stream().map(MusicaResponse::de).toList();
    }

    @GetMapping("/artista/{artista}")
    @Operation(summary = "Buscar músicas por artista")
    public List<MusicaResponse> porArtista(@PathVariable String artista) {
        return catalogoService.buscarPorArtista(artista).stream().map(MusicaResponse::de).toList();
    }

    public record CatalogarMusicaRequest(
            @jakarta.validation.constraints.NotBlank String titulo,
            @jakarta.validation.constraints.NotBlank String artista,
            String album,
            @jakarta.validation.constraints.Positive long duracaoSegundos,
            @jakarta.validation.constraints.NotNull Genero genero
    ) {}

    public record MusicaResponse(String id, String titulo, String artista, String album, long duracaoSegundos, String genero) {
        public static MusicaResponse de(Musica m) {
            return new MusicaResponse(
                    m.id().toString(), m.getTitulo(), m.getArtista(),
                    m.getAlbum(), m.duracao().toSeconds(), m.getGenero().name()
            );
        }
    }
}
