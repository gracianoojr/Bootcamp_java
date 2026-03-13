package app.validation;

import app.dto.UsuarioRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class NumeroValidator implements UsuarioValidator {

    @Override
    public void validar(UsuarioRequestDTO dto) {
        String numero = dto.getNumero();

        if (numero == null || numero.isBlank()) {
            throw new RuntimeException("Número é obrigatório");
        }

        if (!numero.matches("\\d+")) {
            throw new RuntimeException("Número deve conter apenas dígitos");
        }

        if (numero.length() < 8 || numero.length() > 15) {
            throw new RuntimeException("Número deve ter entre 8 a 15 dígitos");
        }
    }
}