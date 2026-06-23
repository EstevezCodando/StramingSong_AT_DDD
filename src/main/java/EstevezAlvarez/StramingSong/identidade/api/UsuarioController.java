package EstevezAlvarez.StramingSong.identidade.api;

import EstevezAlvarez.StramingSong.identidade.application.UsuarioApplicationService;
import EstevezAlvarez.StramingSong.identidade.domain.model.CartaoCredito;
import EstevezAlvarez.StramingSong.identidade.domain.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Identidade e Acesso", description = "Gerenciamento de contas de usuário")
public class UsuarioController {

    private final UsuarioApplicationService usuarioService;

    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    public List<UsuarioResponse> listarTodos() {
        return usuarioService.listarTodos().stream().map(UsuarioResponse::de).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar conta de usuário")
    public UsuarioResponse criarConta(@RequestBody @Valid CriarContaRequest request) {
        Usuario usuario = usuarioService.criarConta(request.nome(), request.email(), request.senha());
        return UsuarioResponse.de(usuario);
    }

    @PostMapping("/{id}/cartao")
    @Operation(summary = "Adicionar cartão de crédito ao usuário")
    public UsuarioResponse adicionarCartao(@PathVariable String id, @RequestBody @Valid AdicionarCartaoRequest request) {
        YearMonth validade = YearMonth.of(request.anoValidade(), request.mesValidade());
        Usuario usuario = usuarioService.adicionarCartao(id, request.ultimos4Digitos(), request.titular(), validade);
        return UsuarioResponse.de(usuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public UsuarioResponse buscarPorId(@PathVariable String id) {
        return UsuarioResponse.de(usuarioService.buscarPorId(id));
    }

    public record CriarContaRequest(
            @jakarta.validation.constraints.NotBlank String nome,
            @jakarta.validation.constraints.Email @jakarta.validation.constraints.NotBlank String email,
            @jakarta.validation.constraints.Size(min = 8) String senha
    ) {}

    public record AdicionarCartaoRequest(
            @jakarta.validation.constraints.Pattern(regexp = "\\d{4}") String ultimos4Digitos,
            @jakarta.validation.constraints.NotBlank String titular,
            int mesValidade,
            int anoValidade
    ) {}

    public record UsuarioResponse(String id, String nome, String email, CartaoInfo cartao) {
        public static UsuarioResponse de(Usuario u) {
            CartaoInfo cartaoInfo = null;
            if (u.cartaoCredito() != null) {
                CartaoCredito c = u.cartaoCredito();
                cartaoInfo = new CartaoInfo(c.ultimos4Digitos(), c.titular(), c.status().name(), c.estaAtivo());
            }
            return new UsuarioResponse(u.id().toString(), u.getNome(), u.email().endereco(), cartaoInfo);
        }
    }

    public record CartaoInfo(String ultimos4Digitos, String titular, String status, boolean ativo) {}
}
