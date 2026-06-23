package EstevezAlvarez.StramingSong.pagamento.domain.rules;

import EstevezAlvarez.StramingSong.pagamento.domain.model.Transacao;
import EstevezAlvarez.StramingSong.pagamento.domain.model.ViolacaoNegocio;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Component
public class TransacaoDuplicadaRule implements RegraAntifraude {

    private static final int LIMITE_SIMILARES = 2;
    private static final Duration JANELA_TEMPO = Duration.ofMinutes(2);

    @Override
    public Optional<ViolacaoNegocio> avaliar(Transacao transacao, ContextoAvaliacao contexto) {
        Instant limiteInferior = transacao.getOcorridaEm().minus(JANELA_TEMPO);

        long transacoesSimilares = contexto.transacoesRecentes().stream()
                .filter(t -> t.getOcorridaEm().isAfter(limiteInferior))
                .filter(t -> t.getComerciante().equals(transacao.getComerciante()))
                .filter(t -> t.getValor().compareTo(transacao.getValor()) == 0)
                .count();

        if (transacoesSimilares >= LIMITE_SIMILARES) {
            return Optional.of(ViolacaoNegocio.de(
                    "transacao-duplicada",
                    "Mais de " + LIMITE_SIMILARES + " transações similares (mesmo valor e comerciante) em 2 minutos"
            ));
        }
        return Optional.empty();
    }
}
