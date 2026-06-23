package EstevezAlvarez.StramingSong.pagamento.application;

import EstevezAlvarez.StramingSong.identidade.domain.model.Usuario;
import EstevezAlvarez.StramingSong.identidade.domain.model.UsuarioId;
import EstevezAlvarez.StramingSong.identidade.domain.repository.UsuarioRepository;
import EstevezAlvarez.StramingSong.pagamento.domain.model.Transacao;
import EstevezAlvarez.StramingSong.pagamento.domain.repository.TransacaoRepository;
import EstevezAlvarez.StramingSong.pagamento.domain.rules.RegraAntifraude;
import EstevezAlvarez.StramingSong.pagamento.domain.service.AutorizacaoTransacaoService;
import EstevezAlvarez.StramingSong.shared.domain.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorizacaoApplicationService {

    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final List<RegraAntifraude> regrasAntifraude;

    @Transactional
    public Transacao autorizarTransacao(String usuarioId, String comerciante, BigDecimal valor) {
        Usuario usuario = usuarioRepository.buscarPorId(UsuarioId.de(usuarioId))
                .orElseThrow(() -> new DomainException("Usuário não encontrado: " + usuarioId));

        Transacao transacao = Transacao.iniciar(usuarioId, comerciante, valor);

        AutorizacaoTransacaoService servico = new AutorizacaoTransacaoService(transacaoRepository, regrasAntifraude);
        return servico.autorizar(transacao, usuario);
    }

    @Transactional(readOnly = true)
    public List<Transacao> listarTransacoesPorUsuario(String usuarioId) {
        return transacaoRepository.buscarPorUsuario(UUID.fromString(usuarioId));
    }
}
