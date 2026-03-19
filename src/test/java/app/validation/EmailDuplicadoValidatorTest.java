package app.validation;

import app.dto.UsuarioRequestDTO;
import app.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailDuplicadoValidatorTest {

    @Test
    void deveLancarErroQuandoEmailJaExistir() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        EmailDuplicadoValidator validator = new EmailDuplicadoValidator(repository);

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "Senha@123"
        );

        when(repository.existsByEmail("joao@email.com")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> validator.validar(dto));

        assertEquals("Email já cadastrado", exception.getMessage());
    }

    @Test
    void naoDeveLancarErroQuandoEmailNaoExistir() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        EmailDuplicadoValidator validator = new EmailDuplicadoValidator(repository);

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "novo@email.com",
                "11999999999",
                "Senha@123"
        );

        when(repository.existsByEmail("novo@email.com")).thenReturn(false);

        assertDoesNotThrow(() -> validator.validar(dto));
    }
}