package EstevezAlvarez.StramingSong.identidade.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.DomainException;

import java.util.Objects;
import java.util.UUID;

public final class UsuarioId {

    private final UUID valor;

    private UsuarioId(UUID valor) {
        this.valor = valor;
    }

    public static UsuarioId novo() {
        return new UsuarioId(UUID.randomUUID());
    }

    public static UsuarioId de(String valor) {
        try {
            return new UsuarioId(UUID.fromString(valor));
        } catch (IllegalArgumentException e) {
            throw new DomainException("ID de usuário inválido: " + valor);
        }
    }

    public static UsuarioId de(UUID valor) {
        Objects.requireNonNull(valor, "ID do usuário não pode ser nulo");
        return new UsuarioId(valor);
    }

    public UUID valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioId that)) return false;
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
