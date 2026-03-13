package app.service;

import app.dto.UsuarioRequestDTO;
import app.dto.UsuarioResponseDTO;
import app.mapper.UsuarioMapper;
import app.model.Usuario;
import app.repository.UsuarioRepository;
import app.validation.UsuarioUpdateValidator;
import app.validation.UsuarioValidator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Test
    void deveCadastrarUsuarioChamandoValidator() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioMapper mapper = mock(UsuarioMapper.class);
        UsuarioValidator validator = mock(UsuarioValidator.class);

        UsuarioService service = new UsuarioService(
                repository,
                mapper,
                List.of(validator),
                List.of()
        );

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "Senha@123"
        );

        Usuario usuario = new Usuario();
        Usuario salvo = new Usuario();
        salvo.setId(1L);

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                1L, "João", "joao@email.com", "11999999999"
        );

        when(mapper.toEntity(dto)).thenReturn(usuario);
        when(repository.save(usuario)).thenReturn(salvo);
        when(mapper.toResponseDTO(salvo)).thenReturn(response);

        UsuarioResponseDTO resultado = service.cadastrar(dto);

        assertNotNull(resultado);
        verify(validator, times(1)).validar(dto);
        verify(repository, times(1)).save(usuario);
    }

    @Test
    void deveBuscarUsuarioPorId() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioMapper mapper = mock(UsuarioMapper.class);

        UsuarioService service = new UsuarioService(
                repository,
                mapper,
                List.of(),
                List.of()
        );

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                1L, "João", "joao@email.com", "11999999999"
        );

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        when(mapper.toResponseDTO(usuario)).thenReturn(response);

        UsuarioResponseDTO resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveExecutarUpdateValidatorsNaAtualizacao() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        UsuarioMapper mapper = mock(UsuarioMapper.class);
        UsuarioUpdateValidator updateValidator = mock(UsuarioUpdateValidator.class);

        UsuarioService service = new UsuarioService(
                repository,
                mapper,
                List.of(),
                List.of(updateValidator)
        );

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Antigo");
        usuario.setEmail("antigo@email.com");
        usuario.setNumero("11999999999");
        usuario.setSenha("Senha@123");

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "Novo",
                "novo@email.com",
                "11888888888",
                "Nova@123"
        );

        Usuario salvo = new Usuario();
        salvo.setId(1L);
        salvo.setNome("Novo");
        salvo.setEmail("novo@email.com");
        salvo.setNumero("11888888888");
        salvo.setSenha("Nova@123");

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                1L, "Novo", "novo@email.com", "11888888888"
        );

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        when(repository.save(usuario)).thenReturn(salvo);
        when(mapper.toResponseDTO(salvo)).thenReturn(response);

        UsuarioResponseDTO resultado = service.atualizar(1L, dto);

        assertNotNull(resultado);
        verify(updateValidator, times(1)).validar(usuario, dto);
    }
}