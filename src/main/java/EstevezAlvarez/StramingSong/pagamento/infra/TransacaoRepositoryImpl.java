package EstevezAlvarez.StramingSong.pagamento.infra;

import EstevezAlvarez.StramingSong.pagamento.domain.model.Transacao;
import EstevezAlvarez.StramingSong.pagamento.domain.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TransacaoRepositoryImpl implements TransacaoRepository {

    private final TransacaoJpaRepository jpa;

    @Override
    public Transacao salvar(Transacao transacao) {
        return jpa.save(transacao);
    }

    @Override
    public Optional<Transacao> buscarPorId(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<Transacao> buscarTransacoesAposInstante(UUID usuarioId, Instant instante) {
        return jpa.findByUsuarioIdAndOcorridaEmAfter(usuarioId, instante);
    }

    @Override
    public List<Transacao> buscarPorUsuario(UUID usuarioId) {
        return jpa.findByUsuarioId(usuarioId);
    }
}
