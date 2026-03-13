package app.validation;

import app.dto.UsuarioRequestDTO;
import app.model.Usuario;
import app.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailDuplicadoUpdateValidatorTest {

    @Test
    void deveLancarErroQuandoAtualizarParaEmailJaExistente() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        EmailDuplicadoUpdateValidator validator = new EmailDuplicadoUpdateValidator(repository);

        Usuario usuarioAtual = new Usuario();
        usuarioAtual.setEmail("antigo@email.com");

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "novo@email.com",
                "11999999999",
                "Senha@123"
        );

        when(repository.existsByEmail("novo@email.com")).thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validator.validar(usuarioAtual, dto)
        );

        assertEquals("Email já cadastrado", exception.getMessage());
    }

    @Test
    void naoDeveLancarErroQuandoEmailForOMesmo() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        EmailDuplicadoUpdateValidator validator = new EmailDuplicadoUpdateValidator(repository);

        Usuario usuarioAtual = new Usuario();
        usuarioAtual.setEmail("mesmo@email.com");

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "mesmo@email.com",
                "11999999999",
                "Senha@123"
        );

        assertDoesNotThrow(() -> validator.validar(usuarioAtual, dto));
        verify(repository, never()).existsByEmail(anyString());
    }

    @Test
    void naoDeveLancarErroQuandoNovoEmailNaoExistir() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        EmailDuplicadoUpdateValidator validator = new EmailDuplicadoUpdateValidator(repository);

        Usuario usuarioAtual = new Usuario();
        usuarioAtual.setEmail("antigo@email.com");

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "novo@email.com",
                "11999999999",
                "Senha@123"
        );

        when(repository.existsByEmail("novo@email.com")).thenReturn(false);

        assertDoesNotThrow(() -> validator.validar(usuarioAtual, dto));
        verify(repository, times(1)).existsByEmail("novo@email.com");
    }
}
