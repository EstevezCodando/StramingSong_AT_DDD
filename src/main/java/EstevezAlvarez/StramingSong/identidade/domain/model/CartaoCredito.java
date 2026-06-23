package EstevezAlvarez.StramingSong.identidade.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.DomainException;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.YearMonth;
import java.util.Objects;

@Embeddable
public class CartaoCredito {

    private String ultimos4Digitos;
    private String titular;
    private YearMonth validade;

    @Enumerated(EnumType.STRING)
    private StatusCartao status;

    protected CartaoCredito() {}

    private CartaoCredito(String ultimos4Digitos, String titular, YearMonth validade) {
        this.ultimos4Digitos = ultimos4Digitos;
        this.titular = titular;
        this.validade = validade;
        this.status = StatusCartao.ATIVO;
    }

    public static CartaoCredito de(String ultimos4Digitos, String titular, YearMonth validade) {
        Objects.requireNonNull(ultimos4Digitos, "Últimos 4 dígitos são obrigatórios");
        Objects.requireNonNull(titular, "Titular é obrigatório");
        Objects.requireNonNull(validade, "Validade é obrigatória");
        if (!ultimos4Digitos.matches("\\d{4}")) {
            throw new DomainException("Últimos 4 dígitos do cartão devem ser numéricos");
        }
        if (validade.isBefore(YearMonth.now())) {
            throw new DomainException("Cartão de crédito vencido");
        }
        return new CartaoCredito(ultimos4Digitos, titular, validade);
    }

    public void bloquear() {
        this.status = StatusCartao.BLOQUEADO;
    }

    public boolean estaAtivo() {
        return StatusCartao.ATIVO.equals(status) && !validade.isBefore(YearMonth.now());
    }

    public String ultimos4Digitos() {
        return ultimos4Digitos;
    }

    public String titular() {
        return titular;
    }

    public YearMonth validade() {
        return validade;
    }

    public StatusCartao status() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartaoCredito that)) return false;
        return Objects.equals(ultimos4Digitos, that.ultimos4Digitos) && Objects.equals(titular, that.titular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ultimos4Digitos, titular);
    }
}
