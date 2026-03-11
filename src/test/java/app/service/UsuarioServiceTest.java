package app.service;

import app.dto.UsuarioRequestDTO;
import app.dto.UsuarioResponseDTO;
import app.model.Usuario;
import app.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "999999",
                "123456"
        );

        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setNome("João");
        usuarioSalvo.setEmail("joao@email.com");
        usuarioSalvo.setNumero("999999");
        usuarioSalvo.setSenha("123456");

        when(repository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        UsuarioResponseDTO resposta = service.cadastrar(dto);

        assertNotNull(resposta);
        assertEquals(1L, resposta.getId());
        assertEquals("João", resposta.getNome());
        assertEquals("joao@email.com", resposta.getEmail());
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    void deveListarUsuarios() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setNumero("888888");
        usuario.setSenha("123");

        when(repository.findAll()).thenReturn(List.of(usuario));

        List<UsuarioResponseDTO> lista = service.listar();

        assertEquals(1, lista.size());
        assertEquals("Maria", lista.get(0).getNome());
    }

    @Test
    void deveBuscarUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Carlos");
        usuario.setEmail("carlos@email.com");
        usuario.setNumero("777777");
        usuario.setSenha("abc");

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO resposta = service.buscarPorId(1L);

        assertNotNull(resposta);
        assertEquals(1L, resposta.getId());
        assertEquals("Carlos", resposta.getNome());
    }

    @Test
    void deveRetornarNullQuandoUsuarioNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        UsuarioResponseDTO resposta = service.buscarPorId(99L);

        assertNull(resposta);
    }

    @Test
    void deveAtualizarUsuario() {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setNome("Antigo");
        usuarioExistente.setEmail("antigo@email.com");
        usuarioExistente.setNumero("111111");
        usuarioExistente.setSenha("old");

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "Novo Nome",
                "novo@email.com",
                "222222",
                "newpass"
        );

        Usuario atualizado = new Usuario();
        atualizado.setId(1L);
        atualizado.setNome("Novo Nome");
        atualizado.setEmail("novo@email.com");
        atualizado.setNumero("222222");
        atualizado.setSenha("newpass");

        when(repository.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(repository.save(any(Usuario.class))).thenReturn(atualizado);

        UsuarioResponseDTO resposta = service.atualizar(1L, dto);

        assertNotNull(resposta);
        assertEquals("Novo Nome", resposta.getNome());
        assertEquals("novo@email.com", resposta.getEmail());
    }

    @Test
    void deveDeletarUsuario() {
        doNothing().when(repository).deleteById(1L);

        service.deletar(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}