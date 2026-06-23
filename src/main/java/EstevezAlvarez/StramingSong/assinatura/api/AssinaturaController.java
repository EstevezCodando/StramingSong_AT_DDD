package EstevezAlvarez.StramingSong.assinatura.api;

import EstevezAlvarez.StramingSong.assinatura.application.AssinaturaApplicationService;
import EstevezAlvarez.StramingSong.assinatura.domain.model.Assinatura;
import EstevezAlvarez.StramingSong.assinatura.domain.model.TipoPlano;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/assinaturas")
@RequiredArgsConstructor
@Tag(name = "Assinatura", description = "Gerenciamento de planos de assinatura")
public class AssinaturaController {

    private final AssinaturaApplicationService assinaturaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Contratar plano de assinatura")
    public AssinaturaResponse contratar(@PathVariable String usuarioId, @RequestBody @Valid ContratarPlanoRequest request) {
        Assinatura assinatura = assinaturaService.contratarPlano(usuarioId, request.tipoPlano(), request.precoMensal());
        return AssinaturaResponse.de(assinatura);
    }

    @GetMapping
    @Operation(summary = "Listar assinaturas do usuário")
    public List<AssinaturaResponse> listar(@PathVariable String usuarioId) {
        return assinaturaService.listarPorUsuario(usuarioId).stream().map(AssinaturaResponse::de).toList();
    }

    @DeleteMapping("/{assinaturaId}")
    @Operation(summary = "Cancelar assinatura")
    public AssinaturaResponse cancelar(@PathVariable String usuarioId, @PathVariable String assinaturaId) {
        return AssinaturaResponse.de(assinaturaService.cancelarPlano(assinaturaId));
    }

    public record ContratarPlanoRequest(
            @jakarta.validation.constraints.NotNull TipoPlano tipoPlano,
            @jakarta.validation.constraints.NotNull BigDecimal precoMensal
    ) {}

    public record AssinaturaResponse(String id, String usuarioId, String tipoPlano, String status,
                                     String dataInicio, String dataRenovacao, BigDecimal precoMensal) {
        public static AssinaturaResponse de(Assinatura a) {
            return new AssinaturaResponse(
                    a.id().toString(), a.getUsuarioId().toString(),
                    a.getTipoPlano().name(), a.getStatus().name(),
                    a.getDataInicio().toString(),
                    a.getDataRenovacao() != null ? a.getDataRenovacao().toString() : null,
                    a.getPrecoMensal()
            );
        }
    }
}
