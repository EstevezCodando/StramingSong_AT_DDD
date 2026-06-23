package EstevezAlvarez.StramingSong.pagamento.domain.repository;

import EstevezAlvarez.StramingSong.pagamento.domain.model.Transacao;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransacaoRepository {
    Transacao salvar(Transacao transacao);
    Optional<Transacao> buscarPorId(UUID id);
    List<Transacao> buscarTransacoesAposInstante(UUID usuarioId, Instant instante);
    List<Transacao> buscarPorUsuario(UUID usuarioId);
}
