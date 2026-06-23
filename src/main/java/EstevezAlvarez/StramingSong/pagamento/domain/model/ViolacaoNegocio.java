package EstevezAlvarez.StramingSong.pagamento.domain.model;

import java.util.Objects;

public final class ViolacaoNegocio {

    private final String codigo;
    private final String descricao;

    private ViolacaoNegocio(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static ViolacaoNegocio de(String codigo, String descricao) {
        Objects.requireNonNull(codigo, "Código da violação não pode ser nulo");
        Objects.requireNonNull(descricao, "Descrição da violação não pode ser nula");
        return new ViolacaoNegocio(codigo, descricao);
    }

    public String codigo() {
        return codigo;
    }

    public String descricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViolacaoNegocio that)) return false;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return codigo + ": " + descricao;
    }
}
