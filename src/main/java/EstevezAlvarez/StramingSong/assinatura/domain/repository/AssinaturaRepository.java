package EstevezAlvarez.StramingSong.assinatura.domain.repository;

import EstevezAlvarez.StramingSong.assinatura.domain.model.Assinatura;
import EstevezAlvarez.StramingSong.assinatura.domain.model.StatusAssinatura;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssinaturaRepository {
    Assinatura salvar(Assinatura assinatura);
    Optional<Assinatura> buscarPorId(UUID id);
    List<Assinatura> buscarPorUsuario(UUID usuarioId);
    List<Assinatura> buscarAtivasPorUsuario(UUID usuarioId);
}
