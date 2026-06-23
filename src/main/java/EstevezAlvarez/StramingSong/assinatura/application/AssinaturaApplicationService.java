package EstevezAlvarez.StramingSong.assinatura.application;

import EstevezAlvarez.StramingSong.assinatura.domain.model.Assinatura;
import EstevezAlvarez.StramingSong.assinatura.domain.model.TipoPlano;
import EstevezAlvarez.StramingSong.assinatura.domain.repository.AssinaturaRepository;
import EstevezAlvarez.StramingSong.assinatura.domain.service.AssinaturaService;
import EstevezAlvarez.StramingSong.shared.domain.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssinaturaApplicationService {

    private final AssinaturaRepository assinaturaRepository;

    @Transactional
    public Assinatura contratarPlano(String usuarioId, TipoPlano tipoPlano, BigDecimal precoMensal) {
        AssinaturaService servico = new AssinaturaService(assinaturaRepository);
        return servico.contratarPlano(usuarioId, tipoPlano, precoMensal);
    }

    @Transactional
    public Assinatura cancelarPlano(String assinaturaId) {
        Assinatura assinatura = buscarOuLancarExcecao(UUID.fromString(assinaturaId));
        assinatura.cancelar();
        return assinaturaRepository.salvar(assinatura);
    }

    @Transactional(readOnly = true)
    public List<Assinatura> listarPorUsuario(String usuarioId) {
        return assinaturaRepository.buscarPorUsuario(UUID.fromString(usuarioId));
    }

    private Assinatura buscarOuLancarExcecao(UUID id) {
        return assinaturaRepository.buscarPorId(id)
                .orElseThrow(() -> new DomainException("Assinatura não encontrada: " + id));
    }
}
