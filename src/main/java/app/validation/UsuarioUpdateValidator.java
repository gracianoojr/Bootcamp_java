package app.validation;

import app.dto.UsuarioRequestDTO;
import app.model.Usuario;

public interface UsuarioUpdateValidator {
    void validar(Usuario usuarioAtual, UsuarioRequestDTO dto);
}