package EstevezAlvarez.StramingSong.identidade.infra;

import EstevezAlvarez.StramingSong.identidade.domain.model.CriptografadorSenha;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptCriptografadorSenha implements CriptografadorSenha {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String criptografar(String senhaTextoSimples) {
        return encoder.encode(senhaTextoSimples);
    }

    @Override
    public boolean confere(String senhaTextoSimples, String hash) {
        return encoder.matches(senhaTextoSimples, hash);
    }
}
