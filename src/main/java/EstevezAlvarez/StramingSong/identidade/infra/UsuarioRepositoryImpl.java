package EstevezAlvarez.StramingSong.identidade.infra;

import EstevezAlvarez.StramingSong.identidade.domain.model.Email;
import EstevezAlvarez.StramingSong.identidade.domain.model.Usuario;
import EstevezAlvarez.StramingSong.identidade.domain.model.UsuarioId;
import EstevezAlvarez.StramingSong.identidade.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository jpa;

    @Override
    public Usuario salvar(Usuario usuario) {
        return jpa.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(UsuarioId id) {
        return jpa.findById(id.valor());
    }

    @Override
    public Optional<Usuario> buscarPorEmail(Email email) {
        return jpa.findByEmail(email.endereco());
    }

    @Override
    public boolean existePorEmail(Email email) {
        return jpa.existsByEmail(email.endereco());
    }

    @Override
    public List<Usuario> listarTodos() {
        return jpa.findAll();
    }
}
