package EstevezAlvarez.StramingSong.biblioteca.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.DomainException;

import java.util.Objects;
import java.util.UUID;

public final class PlaylistId {

    private final UUID valor;

    private PlaylistId(UUID valor) {
        this.valor = valor;
    }

    public static PlaylistId novo() {
        return new PlaylistId(UUID.randomUUID());
    }

    public static PlaylistId de(String valor) {
        try {
            return new PlaylistId(UUID.fromString(valor));
        } catch (IllegalArgumentException e) {
            throw new DomainException("ID de playlist inválido: " + valor);
        }
    }

    public UUID valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaylistId that)) return false;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor.toString();
    }
}
