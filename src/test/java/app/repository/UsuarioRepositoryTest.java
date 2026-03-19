package app.repository;

import app.model.Usuario;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Deve salvar usuário com sucesso")
    void deveSalvarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("João");
        usuario.setEmail("joao@email.com");
        usuario.setNumero("11999999999");
        usuario.setSenha("Senha@123");

        Usuario salvo = repository.save(usuario);

        assertNotNull(salvo);
        assertNotNull(salvo.getId());
        assertEquals("João", salvo.getNome());
        assertEquals("joao@email.com", salvo.getEmail());
    }

    @Test
    @DisplayName("Deve encontrar usuário por email")
    void deveEncontrarUsuarioPorEmail() {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setNumero("11888888888");
        usuario.setSenha("Senha@123");

        repository.save(usuario);

        Optional<Usuario> encontrado = repository.findByEmail("maria@email.com");

        assertTrue(encontrado.isPresent());
        assertEquals("Maria", encontrado.get().getNome());
    }

    @Test
    @DisplayName("Deve verificar se email existe")
    void deveVerificarSeEmailExiste() {
        Usuario usuario = new Usuario();
        usuario.setNome("Carlos");
        usuario.setEmail("carlos@email.com");
        usuario.setNumero("11777777777");
        usuario.setSenha("Senha@123");

        repository.save(usuario);

        boolean existe = repository.existsByEmail("carlos@email.com");

        assertTrue(existe);
    }

    @Test
    @DisplayName("Deve retornar false quando email não existir")
    void deveRetornarFalseQuandoEmailNaoExistir() {
        boolean existe = repository.existsByEmail("naoexiste@email.com");

        assertFalse(existe);
    }
}
