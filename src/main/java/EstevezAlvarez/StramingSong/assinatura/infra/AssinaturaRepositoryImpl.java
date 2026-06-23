package EstevezAlvarez.StramingSong.assinatura.infra;

import EstevezAlvarez.StramingSong.assinatura.domain.model.Assinatura;
import EstevezAlvarez.StramingSong.assinatura.domain.model.StatusAssinatura;
import EstevezAlvarez.StramingSong.assinatura.domain.repository.AssinaturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AssinaturaRepositoryImpl implements AssinaturaRepository {

    private final AssinaturaJpaRepository jpa;

    @Override
    public Assinatura salvar(Assinatura assinatura) {
        return jpa.save(assinatura);
    }

    @Override
    public Optional<Assinatura> buscarPorId(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<Assinatura> buscarPorUsuario(UUID usuarioId) {
        return jpa.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Assinatura> buscarAtivasPorUsuario(UUID usuarioId) {
        return jpa.findByUsuarioIdAndStatus(usuarioId, StatusAssinatura.ATIVA);
    }
}
