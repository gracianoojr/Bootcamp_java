package app.mapper;

import app.dto.UsuarioRequestDTO;
import app.dto.UsuarioResponseDTO;
import app.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioMapperTest {

    private final UsuarioMapper mapper = new UsuarioMapper();

    @Test
    void deveConverterRequestDTOParaEntity() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "João",
                "joao@email.com",
                "11999999999",
                "Senha@123"
        );

        Usuario usuario = mapper.toEntity(dto);

        assertNotNull(usuario);
        assertEquals("João", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals("11999999999", usuario.getNumero());
        assertEquals("Senha@123", usuario.getSenha());
    }

    @Test
    void deveConverterEntityParaResponseDTO() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setNumero("11888888888");
        usuario.setSenha("Senha@123");

        UsuarioResponseDTO dto = mapper.toResponseDTO(usuario);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Maria", dto.getNome());
        assertEquals("maria@email.com", dto.getEmail());
        assertEquals("11888888888", dto.getNumero());
    }
}