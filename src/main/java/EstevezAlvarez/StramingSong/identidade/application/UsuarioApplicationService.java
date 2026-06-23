package EstevezAlvarez.StramingSong.identidade.application;

import EstevezAlvarez.StramingSong.identidade.domain.model.*;
import EstevezAlvarez.StramingSong.identidade.domain.repository.UsuarioRepository;
import EstevezAlvarez.StramingSong.identidade.domain.service.CadastroUsuarioService;
import EstevezAlvarez.StramingSong.shared.domain.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioApplicationService {

    private final UsuarioRepository usuarioRepository;
    private final CriptografadorSenha criptografadorSenha;

    @Transactional
    public Usuario criarConta(String nome, String email, String senha) {
        CadastroUsuarioService servico = new CadastroUsuarioService(usuarioRepository, criptografadorSenha);
        return servico.criarConta(nome, email, senha);
    }

    @Transactional
    public Usuario adicionarCartao(String usuarioId, String ultimos4Digitos, String titular, YearMonth validade) {
        Usuario usuario = buscarOuLancarExcecao(UsuarioId.de(usuarioId));
        usuario.adicionarCartaoCredito(ultimos4Digitos, titular, validade);
        return usuarioRepository.salvar(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(String usuarioId) {
        return buscarOuLancarExcecao(UsuarioId.de(usuarioId));
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.listarTodos();
    }

    private Usuario buscarOuLancarExcecao(UsuarioId id) {
        return usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new DomainException("Usuário não encontrado: " + id));
    }
}
