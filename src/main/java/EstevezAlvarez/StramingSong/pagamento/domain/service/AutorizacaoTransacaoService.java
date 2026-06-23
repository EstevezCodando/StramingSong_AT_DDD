package EstevezAlvarez.StramingSong.pagamento.domain.service;

import EstevezAlvarez.StramingSong.identidade.domain.model.Usuario;
import EstevezAlvarez.StramingSong.pagamento.domain.model.Transacao;
import EstevezAlvarez.StramingSong.pagamento.domain.model.ViolacaoNegocio;
import EstevezAlvarez.StramingSong.pagamento.domain.repository.TransacaoRepository;
import EstevezAlvarez.StramingSong.pagamento.domain.rules.ContextoAvaliacao;
import EstevezAlvarez.StramingSong.pagamento.domain.rules.RegraAntifraude;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * Domain Service que orquestra a avaliação das regras antifraude.
 * Utiliza o padrão Strategy: cada RegraAntifraude é avaliada de forma independente.
 */
public class AutorizacaoTransacaoService {

    private static final Duration JANELA_HISTORICO = Duration.ofMinutes(2);

    private final TransacaoRepository transacaoRepository;
    private final List<RegraAntifraude> regrasAntifraude;

    public AutorizacaoTransacaoService(TransacaoRepository transacaoRepository, List<RegraAntifraude> regrasAntifraude) {
        this.transacaoRepository = transacaoRepository;
        this.regrasAntifraude = regrasAntifraude;
    }

    public Transacao autorizar(Transacao transacao, Usuario usuario) {
        Instant limiteHistorico = transacao.getOcorridaEm().minus(JANELA_HISTORICO);
        List<Transacao> historico = transacaoRepository.buscarTransacoesAposInstante(usuario.id().valor(), limiteHistorico);

        ContextoAvaliacao contexto = new ContextoAvaliacao(usuario, historico);

        List<ViolacaoNegocio> violacoes = regrasAntifraude.stream()
                .map(regra -> regra.avaliar(transacao, contexto))
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .toList();

        if (!violacoes.isEmpty()) {
            transacao.rejeitar(violacoes);
        }

        return transacaoRepository.salvar(transacao);
    }
}
