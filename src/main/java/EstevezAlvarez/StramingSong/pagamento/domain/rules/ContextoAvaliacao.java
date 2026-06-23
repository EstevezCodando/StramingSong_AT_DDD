package EstevezAlvarez.StramingSong.pagamento.domain.rules;

import EstevezAlvarez.StramingSong.identidade.domain.model.Usuario;
import EstevezAlvarez.StramingSong.pagamento.domain.model.Transacao;

import java.util.List;

/**
 * Contexto passado para avaliação das regras antifraude.
 * Agrega os dados necessários sem criar dependências cruzadas nos aggregates.
 */
public record ContextoAvaliacao(
        Usuario usuario,
        List<Transacao> transacoesRecentes
) {}
