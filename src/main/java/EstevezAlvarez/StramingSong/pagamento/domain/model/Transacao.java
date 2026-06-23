package EstevezAlvarez.StramingSong.pagamento.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.AggregateRoot;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "transacoes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transacao extends AggregateRoot {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "usuario_id", nullable = false, columnDefinition = "uuid")
    @Getter
    private UUID usuarioId;

    @Column(name = "comerciante", nullable = false)
    @Getter
    private String comerciante;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    @Getter
    private BigDecimal valor;

    @Column(name = "ocorrida_em", nullable = false)
    @Getter
    private Instant ocorridaEm;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Getter
    private StatusTransacao status;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "transacao_violacoes", joinColumns = @JoinColumn(name = "transacao_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "codigo", column = @Column(name = "codigo")),
            @AttributeOverride(name = "descricao", column = @Column(name = "descricao"))
    })
    private List<ViolacaoNegocioJpa> violacoes = new ArrayList<>();

    private Transacao(UUID id, UUID usuarioId, String comerciante, BigDecimal valor) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.comerciante = comerciante;
        this.valor = valor;
        this.ocorridaEm = Instant.now();
        this.status = StatusTransacao.APROVADA;
    }

    public static Transacao iniciar(String usuarioId, String comerciante, BigDecimal valor) {
        return new Transacao(UUID.randomUUID(), UUID.fromString(usuarioId), comerciante, valor);
    }

    public void rejeitar(List<ViolacaoNegocio> violacoesEncontradas) {
        this.status = StatusTransacao.REJEITADA;
        violacoesEncontradas.forEach(v ->
                this.violacoes.add(new ViolacaoNegocioJpa(v.codigo(), v.descricao()))
        );
    }

    public boolean foiAprovada() {
        return StatusTransacao.APROVADA.equals(status);
    }

    public UUID id() {
        return id;
    }

    public List<ViolacaoNegocio> violacoes() {
        return violacoes.stream()
                .map(v -> ViolacaoNegocio.de(v.getCodigo(), v.getDescricao()))
                .toList();
    }
}
