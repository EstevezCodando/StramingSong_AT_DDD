package EstevezAlvarez.StramingSong.pagamento.domain.rules;

import EstevezAlvarez.StramingSong.pagamento.domain.model.Transacao;
import EstevezAlvarez.StramingSong.pagamento.domain.model.ViolacaoNegocio;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Component
public class AltaFrequenciaPequenoIntervaloRule implements RegraAntifraude {

    private static final int LIMITE_TRANSACOES = 3;
    private static final Duration JANELA_TEMPO = Duration.ofMinutes(2);

    @Override
    public Optional<ViolacaoNegocio> avaliar(Transacao transacao, ContextoAvaliacao contexto) {
        Instant limiteInferior = transacao.getOcorridaEm().minus(JANELA_TEMPO);

        long transacoesNaJanela = contexto.transacoesRecentes().stream()
                .filter(t -> t.getOcorridaEm().isAfter(limiteInferior))
                .count();

        if (transacoesNaJanela >= LIMITE_TRANSACOES) {
            return Optional.of(ViolacaoNegocio.de(
                    "alta-frequencia-pequeno-intervalo",
                    "Mais de " + LIMITE_TRANSACOES + " transações em um intervalo de 2 minutos"
            ));
        }
        return Optional.empty();
    }
}
