package app.validation;

import app.dto.UsuarioRequestDTO;
import app.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class EmailDuplicadoValidator implements UsuarioValidator {

    private final UsuarioRepository repository;

    public EmailDuplicadoValidator(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validar(UsuarioRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
    }
}