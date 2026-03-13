package app.mapper;

import app.dto.UsuarioRequestDTO;
import app.dto.UsuarioResponseDTO;
import app.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setNumero(dto.getNumero());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getNumero()
        );
    }
}