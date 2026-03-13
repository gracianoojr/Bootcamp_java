package app.validation;

import app.dto.UsuarioRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SenhaForteValidatorTest {

    private final SenhaForteValidator validator = new SenhaForteValidator();

    @Test
    void deveAceitarSenhaForte() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "Senha@123"
        );

        assertDoesNotThrow(() -> validator.validar(dto));
    }

    @Test
    void deveLancarErroQuandoSenhaForCurta() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "Ab1@"
        );

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> validator.validar(dto));

        assertEquals("Senha deve ter pelo menos 8 caracteres", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoSenhaNaoTiverMaiuscula() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "senha@123"
        );

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> validator.validar(dto));

        assertEquals("Senha deve conter pelo menos uma letra maiúscula", exception.getMessage());
    }
}