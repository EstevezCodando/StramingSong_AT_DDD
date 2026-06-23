package EstevezAlvarez.StramingSong.identidade.domain.service;

import EstevezAlvarez.StramingSong.identidade.domain.model.*;
import EstevezAlvarez.StramingSong.identidade.domain.repository.UsuarioRepository;
import EstevezAlvarez.StramingSong.shared.domain.DomainException;

public class CadastroUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CriptografadorSenha criptografadorSenha;

    public CadastroUsuarioService(UsuarioRepository usuarioRepository, CriptografadorSenha criptografadorSenha) {
        this.usuarioRepository = usuarioRepository;
        this.criptografadorSenha = criptografadorSenha;
    }

    public Usuario criarConta(String nome, String email, String senha) {
        Email emailDominio = Email.de(email);
        if (usuarioRepository.existePorEmail(emailDominio)) {
            throw new DomainException("Já existe uma conta com o e-mail: " + email);
        }
        Usuario usuario = Usuario.criar(nome, email, senha, criptografadorSenha);
        return usuarioRepository.salvar(usuario);
    }
}
