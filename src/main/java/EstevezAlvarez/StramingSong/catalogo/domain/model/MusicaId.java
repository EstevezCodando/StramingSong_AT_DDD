package EstevezAlvarez.StramingSong.catalogo.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.DomainException;

import java.util.Objects;
import java.util.UUID;

public final class MusicaId {

    private final UUID valor;

    private MusicaId(UUID valor) {
        this.valor = valor;
    }

    public static MusicaId novo() {
        return new MusicaId(UUID.randomUUID());
    }

    public static MusicaId de(String valor) {
        try {
            return new MusicaId(UUID.fromString(valor));
        } catch (IllegalArgumentException e) {
            throw new DomainException("ID de música inválido: " + valor);
        }
    }

    public UUID valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicaId that)) return false;
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
