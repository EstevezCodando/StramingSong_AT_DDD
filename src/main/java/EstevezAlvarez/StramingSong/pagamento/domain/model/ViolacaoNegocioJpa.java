package EstevezAlvarez.StramingSong.pagamento.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ViolacaoNegocioJpa {
    private String codigo;
    private String descricao;
}
