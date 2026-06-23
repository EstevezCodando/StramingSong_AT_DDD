package EstevezAlvarez.StramingSong.identidade.domain.model;

public interface CriptografadorSenha {
    String criptografar(String senhaTextoSimples);
    boolean confere(String senhaTextoSimples, String hash);
}
