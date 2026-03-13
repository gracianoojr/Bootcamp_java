package app.validation;

import app.dto.UsuarioRequestDTO;
import app.model.Usuario;
import app.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class EmailDuplicadoUpdateValidator implements UsuarioUpdateValidator {

    private final UsuarioRepository repository;

    public EmailDuplicadoUpdateValidator(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validar(Usuario usuarioAtual, UsuarioRequestDTO dto) {
        if (!usuarioAtual.getEmail().equals(dto.getEmail())
                && repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
    }
}