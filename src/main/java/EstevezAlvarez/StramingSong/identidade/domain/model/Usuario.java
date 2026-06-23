package EstevezAlvarez.StramingSong.identidade.domain.model;

import EstevezAlvarez.StramingSong.shared.domain.AggregateRoot;
import EstevezAlvarez.StramingSong.shared.domain.DomainException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Usuario extends AggregateRoot {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "nome", nullable = false)
    @Getter
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    @Embedded
    private CartaoCredito cartaoCredito;

    private Usuario(UsuarioId id, String nome, Email email, Senha senha) {
        this.id = id.valor();
        this.nome = nome;
        this.email = email.endereco();
        this.senhaHash = senha.hashBcrypt();
    }

    public static Usuario criar(String nome, String email, String senha, CriptografadorSenha criptografador) {
        validarNome(nome);
        Email emailValido = Email.de(email);
        Senha senhaValida = Senha.deTextoSimples(senha, criptografador);
        return new Usuario(UsuarioId.novo(), nome.trim(), emailValido, senhaValida);
    }

    public void adicionarCartaoCredito(String ultimos4Digitos, String titular, YearMonth validade) {
        this.cartaoCredito = CartaoCredito.de(ultimos4Digitos, titular, validade);
    }

    public void bloquearCartao() {
        if (cartaoCredito == null) {
            throw new DomainException("Usuário não possui cartão de crédito cadastrado");
        }
        cartaoCredito.bloquear();
    }

    public boolean possuiCartaoValido() {
        return cartaoCredito != null && cartaoCredito.estaAtivo();
    }

    public UsuarioId id() {
        return UsuarioId.de(id);
    }

    public Email email() {
        return Email.de(email);
    }

    public CartaoCredito cartaoCredito() {
        return cartaoCredito;
    }

    private static void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new DomainException("Nome do usuário é obrigatório");
        }
        if (nome.trim().length() < 2) {
            throw new DomainException("Nome do usuário deve ter no mínimo 2 caracteres");
        }
    }
}
