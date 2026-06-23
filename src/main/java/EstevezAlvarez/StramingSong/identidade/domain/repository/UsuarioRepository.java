package EstevezAlvarez.StramingSong.identidade.domain.repository;

import EstevezAlvarez.StramingSong.identidade.domain.model.Email;
import EstevezAlvarez.StramingSong.identidade.domain.model.Usuario;
import EstevezAlvarez.StramingSong.identidade.domain.model.UsuarioId;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(UsuarioId id);
    Optional<Usuario> buscarPorEmail(Email email);
    boolean existePorEmail(Email email);
    List<Usuario> listarTodos();
}
