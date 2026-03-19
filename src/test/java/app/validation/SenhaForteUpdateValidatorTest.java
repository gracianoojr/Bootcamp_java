package app.validation;

import app.dto.UsuarioRequestDTO;
import app.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SenhaForteUpdateValidatorTest {

    private final SenhaForteUpdateValidator validator = new SenhaForteUpdateValidator();

    @Test
    void deveAceitarSenhaForteNoUpdate() {
        Usuario usuarioAtual = new Usuario();

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "Senha@123"
        );

        assertDoesNotThrow(() -> validator.validar(usuarioAtual, dto));
    }

    @Test
    void deveLancarErroQuandoSenhaForVaziaNoUpdate() {
        Usuario usuarioAtual = new Usuario();

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                ""
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validator.validar(usuarioAtual, dto)
        );

        assertEquals("Senha é obrigatória", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoSenhaForCurtaNoUpdate() {
        Usuario usuarioAtual = new Usuario();

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "Ab1@"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validator.validar(usuarioAtual, dto)
        );

        assertEquals("Senha deve ter pelo menos 8 caracteres", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoSenhaNaoTiverMaiusculaNoUpdate() {
        Usuario usuarioAtual = new Usuario();

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "senha@123"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validator.validar(usuarioAtual, dto)
        );

        assertEquals("Senha deve conter pelo menos uma letra maiúscula", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoSenhaNaoTiverMinusculaNoUpdate() {
        Usuario usuarioAtual = new Usuario();

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "SENHA@123"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validator.validar(usuarioAtual, dto)
        );

        assertEquals("Senha deve conter pelo menos uma letra minúscula", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoSenhaNaoTiverNumeroNoUpdate() {
        Usuario usuarioAtual = new Usuario();

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "Senha@abc"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validator.validar(usuarioAtual, dto)
        );

        assertEquals("Senha deve conter pelo menos um número", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoSenhaNaoTiverCaractereEspecialNoUpdate() {
        Usuario usuarioAtual = new Usuario();

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "Senha1234"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validator.validar(usuarioAtual, dto)
        );

        assertEquals("Senha deve conter pelo menos um caractere especial", exception.getMessage());
    }
}
