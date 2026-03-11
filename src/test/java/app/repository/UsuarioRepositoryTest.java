package app.repository;

import app.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    void deveSalvarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("João");
        usuario.setEmail("joao@email.com");
        usuario.setNumero("999999");
        usuario.setSenha("123456");

        Usuario salvo = repository.save(usuario);

        assertNotNull(salvo.getId());
        assertEquals("João", salvo.getNome());
    }

    @Test
    void deveBuscarUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setNumero("888888");
        usuario.setSenha("abc");

        Usuario salvo = repository.save(usuario);

        Optional<Usuario> encontrado = repository.findById(salvo.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("Maria", encontrado.get().getNome());
    }

    @Test
    void deveDeletarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Carlos");
        usuario.setEmail("carlos@email.com");
        usuario.setNumero("777777");
        usuario.setSenha("123");

        Usuario salvo = repository.save(usuario);
        Long id = salvo.getId();

        repository.deleteById(id);

        Optional<Usuario> encontrado = repository.findById(id);
        assertFalse(encontrado.isPresent());
    }
}