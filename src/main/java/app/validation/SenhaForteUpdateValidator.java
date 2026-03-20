package app.validation;

import app.dto.UsuarioRequestDTO;
import app.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class SenhaForteUpdateValidator implements UsuarioUpdateValidator {

    @Override
    public void validar(Usuario usuarioAtual, UsuarioRequestDTO dto) {
        String senha = dto.getSenha();

        if (senha == null || senha.isBlank()) {
            return;
        }

        if (senha.length() < 8) {
            throw new RuntimeException("Senha deve ter pelo menos 8 caracteres");
        }

        if (!senha.matches(".*[A-Z].*")) {
            throw new RuntimeException("Senha deve conter pelo menos uma letra maiúscula");
        }

        if (!senha.matches(".*[a-z].*")) {
            throw new RuntimeException("Senha deve conter pelo menos uma letra minúscula");
        }

        if (!senha.matches(".*\\d.*")) {
            throw new RuntimeException("Senha deve conter pelo menos um número");
        }

        if (!senha.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            throw new RuntimeException("Senha deve conter pelo menos um caractere especial");
        }
    }
}