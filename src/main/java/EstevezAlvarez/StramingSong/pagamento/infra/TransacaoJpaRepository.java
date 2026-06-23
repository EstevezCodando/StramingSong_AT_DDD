package EstevezAlvarez.StramingSong.pagamento.infra;

import EstevezAlvarez.StramingSong.pagamento.domain.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

interface TransacaoJpaRepository extends JpaRepository<Transacao, UUID> {
    List<Transacao> findByUsuarioId(UUID usuarioId);
    List<Transacao> findByUsuarioIdAndOcorridaEmAfter(UUID usuarioId, Instant instante);
}
