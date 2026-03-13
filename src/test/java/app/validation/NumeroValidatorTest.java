package app.validation;

import app.dto.UsuarioRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumeroValidatorTest {

    private final NumeroValidator validator = new NumeroValidator();

    @Test
    void deveAceitarNumeroValido() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "Senha@123"
        );

        assertDoesNotThrow(() -> validator.validar(dto));
    }

    @Test
    void deveLancarErroQuandoNumeroForVazio() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "",
                "Senha@123"
        );

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> validator.validar(dto));

        assertEquals("Número é obrigatório", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoNumeroTiverLetras() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11abc999",
                "Senha@123"
        );

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> validator.validar(dto));

        assertEquals("Número deve conter apenas dígitos", exception.getMessage());
    }
}