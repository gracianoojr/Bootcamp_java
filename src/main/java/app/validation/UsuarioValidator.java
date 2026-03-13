package app.validation;

import app.dto.UsuarioRequestDTO;

public interface UsuarioValidator {
    void validar(UsuarioRequestDTO dto);
}