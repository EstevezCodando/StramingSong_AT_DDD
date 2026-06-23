package EstevezAlvarez.StramingSong.catalogo.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.AggregateRoot;
import EstevezAlvarez.StramingSong.shared.domain.DomainException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.UUID;

@Entity
@Table(name = "musicas")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Musica extends AggregateRoot {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "titulo", nullable = false)
    @Getter
    private String titulo;

    @Column(name = "artista", nullable = false)
    @Getter
    private String artista;

    @Column(name = "album")
    @Getter
    private String album;

    @Column(name = "duracao_segundos", nullable = false)
    private long duracaoSegundos;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false)
    @Getter
    private Genero genero;

    private Musica(MusicaId id, String titulo, String artista, String album, Duration duracao, Genero genero) {
        this.id = id.valor();
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.duracaoSegundos = duracao.toSeconds();
        this.genero = genero;
    }

    public static Musica catalogar(String titulo, String artista, String album, Duration duracao, Genero genero) {
        validarTitulo(titulo);
        validarArtista(artista);
        validarDuracao(duracao);
        return new Musica(MusicaId.novo(), titulo.trim(), artista.trim(), album != null ? album.trim() : null, duracao, genero);
    }

    public MusicaId id() {
        return MusicaId.de(id.toString());
    }

    public Duration duracao() {
        return Duration.ofSeconds(duracaoSegundos);
    }

    private static void validarTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new DomainException("Título da música é obrigatório");
        }
    }

    private static void validarArtista(String artista) {
        if (artista == null || artista.isBlank()) {
            throw new DomainException("Artista da música é obrigatório");
        }
    }

    private static void validarDuracao(Duration duracao) {
        if (duracao == null || duracao.isNegative() || duracao.isZero()) {
            throw new DomainException("Duração da música deve ser positiva");
        }
    }
}
