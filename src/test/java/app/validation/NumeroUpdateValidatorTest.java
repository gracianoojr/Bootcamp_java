package app.validation;

import app.dto.UsuarioRequestDTO;
import app.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumeroUpdateValidatorTest {

    private final NumeroUpdateValidator validator = new NumeroUpdateValidator();

    @Test
    void deveAceitarNumeroValidoNoUpdate() {
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
    void deveLancarErroQuandoNumeroForVazioNoUpdate() {
        Usuario usuarioAtual = new Usuario();

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "",
                "Senha@123"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validator.validar(usuarioAtual, dto)
        );

        assertEquals("Número é obrigatório", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoNumeroTiverLetrasNoUpdate() {
        Usuario usuarioAtual = new Usuario();

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11abc999",
                "Senha@123"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validator.validar(usuarioAtual, dto)
        );

        assertEquals("Número deve conter apenas dígitos", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoNumeroForMuitoCurtoNoUpdate() {
        Usuario usuarioAtual = new Usuario();

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "1234",
                "Senha@123"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validator.validar(usuarioAtual, dto)
        );

        assertEquals("Número deve ter entre 8 a 15 dígitos", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoNumeroForMuitoLongoNoUpdate() {
        Usuario usuarioAtual = new Usuario();

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "1234567890123456",
                "Senha@123"
        );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validator.validar(usuarioAtual, dto)
        );

        assertEquals("Número deve ter entre 8 a 15 dígitos", exception.getMessage());
    }
}
