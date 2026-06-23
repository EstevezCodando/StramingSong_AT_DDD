package EstevezAlvarez.StramingSong.assinatura.infra;

import EstevezAlvarez.StramingSong.assinatura.domain.model.Assinatura;
import EstevezAlvarez.StramingSong.assinatura.domain.model.StatusAssinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface AssinaturaJpaRepository extends JpaRepository<Assinatura, UUID> {
    List<Assinatura> findByUsuarioId(UUID usuarioId);
    List<Assinatura> findByUsuarioIdAndStatus(UUID usuarioId, StatusAssinatura status);
}
