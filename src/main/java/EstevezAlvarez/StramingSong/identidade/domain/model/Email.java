package EstevezAlvarez.StramingSong.identidade.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.DomainException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Email {

    private static final Pattern FORMATO_EMAIL = Pattern.compile("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$");

    private final String endereco;

    private Email(String endereco) {
        this.endereco = endereco;
    }

    public static Email de(String endereco) {
        Objects.requireNonNull(endereco, "E-mail não pode ser nulo");
        String normalizado = endereco.trim().toLowerCase();
        if (!FORMATO_EMAIL.matcher(normalizado).matches()) {
            throw new DomainException("Formato de e-mail inválido: " + endereco);
        }
        return new Email(normalizado);
    }

    public String endereco() {
        return endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email that)) return false;
        return Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endereco);
    }

    @Override
    public String toString() {
        return endereco;
    }
}
