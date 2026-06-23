package EstevezAlvarez.StramingSong.pagamento.domain.rules;

import EstevezAlvarez.StramingSong.pagamento.domain.model.Transacao;
import EstevezAlvarez.StramingSong.pagamento.domain.model.ViolacaoNegocio;

import java.util.List;
import java.util.Optional;

/**
 * Interface Strategy para regras antifraude.
 * Novas regras são adicionadas implementando esta interface sem modificar código existente (OCP).
 */
public interface RegraAntifraude {
    Optional<ViolacaoNegocio> avaliar(Transacao transacao, ContextoAvaliacao contexto);
}
