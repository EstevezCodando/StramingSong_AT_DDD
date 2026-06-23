package EstevezAlvarez.StramingSong.pagamento.api;

import EstevezAlvarez.StramingSong.pagamento.application.AutorizacaoApplicationService;
import EstevezAlvarez.StramingSong.pagamento.domain.model.Transacao;
import EstevezAlvarez.StramingSong.pagamento.domain.model.ViolacaoNegocio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor
@Tag(name = "Pagamento e Antifraude", description = "Autorização de transações e controle antifraude")
public class TransacaoController {

    private final AutorizacaoApplicationService autorizacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Autorizar transação")
    public TransacaoResponse autorizar(@RequestBody @Valid AutorizarTransacaoRequest request) {
        Transacao transacao = autorizacaoService.autorizarTransacao(
                request.usuarioId(), request.comerciante(), request.valor()
        );
        return TransacaoResponse.de(transacao);
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar transações do usuário")
    public List<TransacaoResponse> listarPorUsuario(@PathVariable String usuarioId) {
        return autorizacaoService.listarTransacoesPorUsuario(usuarioId)
                .stream().map(TransacaoResponse::de).toList();
    }

    public record AutorizarTransacaoRequest(
            @jakarta.validation.constraints.NotBlank String usuarioId,
            @jakarta.validation.constraints.NotBlank String comerciante,
            @jakarta.validation.constraints.NotNull @jakarta.validation.constraints.Positive BigDecimal valor
    ) {}

    public record TransacaoResponse(String id, String usuarioId, String comerciante, BigDecimal valor,
                                    String ocorridaEm, String status, List<ViolacaoResponse> violacoes) {
        public static TransacaoResponse de(Transacao t) {
            List<ViolacaoResponse> violacoes = t.violacoes().stream()
                    .map(v -> new ViolacaoResponse(v.codigo(), v.descricao()))
                    .toList();
            return new TransacaoResponse(
                    t.id().toString(), t.getUsuarioId().toString(), t.getComerciante(),
                    t.getValor(), t.getOcorridaEm().toString(), t.getStatus().name(), violacoes
            );
        }
    }

    public record ViolacaoResponse(String codigo, String descricao) {}
}
