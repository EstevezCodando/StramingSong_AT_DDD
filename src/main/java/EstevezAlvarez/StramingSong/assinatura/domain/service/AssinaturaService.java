package EstevezAlvarez.StramingSong.assinatura.domain.service;

import EstevezAlvarez.StramingSong.assinatura.domain.model.Assinatura;
import EstevezAlvarez.StramingSong.assinatura.domain.model.TipoPlano;
import EstevezAlvarez.StramingSong.assinatura.domain.repository.AssinaturaRepository;
import EstevezAlvarez.StramingSong.shared.domain.DomainException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Domain Service para regras de assinatura que envolvem mais de um aggregate.
 * Garante que o usuário tenha no máximo um plano ativo (regra de negócio antifraude).
 */
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
    }

    public Assinatura contratarPlano(String usuarioId, TipoPlano tipoPlano, BigDecimal precoMensal) {
        UUID id = UUID.fromString(usuarioId);
        List<Assinatura> ativas = assinaturaRepository.buscarAtivasPorUsuario(id);
        if (!ativas.isEmpty()) {
            throw new DomainException("usuario-com-plano-ativo: O usuário já possui um plano ativo");
        }
        Assinatura assinatura = Assinatura.contratar(usuarioId, tipoPlano, precoMensal);
        return assinaturaRepository.salvar(assinatura);
    }
}
