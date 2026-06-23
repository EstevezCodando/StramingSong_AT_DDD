package EstevezAlvarez.StramingSong.pagamento.domain.rules;

import EstevezAlvarez.StramingSong.pagamento.domain.model.Transacao;
import EstevezAlvarez.StramingSong.pagamento.domain.model.ViolacaoNegocio;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CartaoInativoRule implements RegraAntifraude {

    @Override
    public Optional<ViolacaoNegocio> avaliar(Transacao transacao, ContextoAvaliacao contexto) {
        if (!contexto.usuario().possuiCartaoValido()) {
            return Optional.of(ViolacaoNegocio.de(
                    "cartao-inativo",
                    "Nenhuma transação aceita quando o cartão não está ativo"
            ));
        }
        return Optional.empty();
    }
}
