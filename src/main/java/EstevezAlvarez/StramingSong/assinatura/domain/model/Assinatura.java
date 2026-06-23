package EstevezAlvarez.StramingSong.assinatura.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.AggregateRoot;
import EstevezAlvarez.StramingSong.shared.domain.DomainException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "assinaturas")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Assinatura extends AggregateRoot {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "usuario_id", nullable = false, columnDefinition = "uuid")
    @Getter
    private UUID usuarioId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_plano", nullable = false)
    @Getter
    private TipoPlano tipoPlano;

    @Column(name = "preco_mensal", nullable = false, precision = 10, scale = 2)
    @Getter
    private BigDecimal precoMensal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Getter
    private StatusAssinatura status;

    @Column(name = "data_inicio", nullable = false)
    @Getter
    private LocalDate dataInicio;

    @Column(name = "data_renovacao")
    @Getter
    private LocalDate dataRenovacao;

    private Assinatura(UUID id, UUID usuarioId, TipoPlano tipoPlano, BigDecimal precoMensal) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.tipoPlano = tipoPlano;
        this.precoMensal = precoMensal;
        this.status = StatusAssinatura.ATIVA;
        this.dataInicio = LocalDate.now();
        this.dataRenovacao = LocalDate.now().plusMonths(1);
    }

    public static Assinatura contratar(String usuarioId, TipoPlano tipoPlano, BigDecimal precoMensal) {
        return new Assinatura(UUID.randomUUID(), UUID.fromString(usuarioId), tipoPlano, precoMensal);
    }

    public void cancelar() {
        if (StatusAssinatura.CANCELADA.equals(status)) {
            throw new DomainException("Assinatura já está cancelada");
        }
        this.status = StatusAssinatura.CANCELADA;
    }

    public void renovar() {
        if (!StatusAssinatura.ATIVA.equals(status)) {
            throw new DomainException("Apenas assinaturas ativas podem ser renovadas");
        }
        this.dataRenovacao = dataRenovacao.plusMonths(1);
    }

    public boolean estaAtiva() {
        return StatusAssinatura.ATIVA.equals(status);
    }

    public UUID id() {
        return id;
    }
}
