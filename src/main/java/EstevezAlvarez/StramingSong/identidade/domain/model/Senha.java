package EstevezAlvarez.StramingSong.identidade.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.DomainException;

import java.util.Objects;

public final class Senha {

    private static final int TAMANHO_MINIMO = 8;

    private final String hashBcrypt;

    private Senha(String hashBcrypt) {
        this.hashBcrypt = hashBcrypt;
    }

    public static Senha deTextoSimples(String senha, CriptografadorSenha criptografador) {
        Objects.requireNonNull(senha, "Senha não pode ser nula");
        if (senha.length() < TAMANHO_MINIMO) {
            throw new DomainException("Senha deve ter no mínimo " + TAMANHO_MINIMO + " caracteres");
        }
        return new Senha(criptografador.criptografar(senha));
    }

    public static Senha deHash(String hashBcrypt) {
        Objects.requireNonNull(hashBcrypt, "Hash da senha não pode ser nulo");
        return new Senha(hashBcrypt);
    }

    public boolean confere(String senhaTextoSimples, CriptografadorSenha criptografador) {
        return criptografador.confere(senhaTextoSimples, hashBcrypt);
    }

    public String hashBcrypt() {
        return hashBcrypt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Senha that)) return false;
        return Objects.equals(hashBcrypt, that.hashBcrypt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashBcrypt);
    }

    @Override
    public String toString() {
        return "[PROTEGIDO]";
    }
}
