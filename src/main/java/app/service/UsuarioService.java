package app.service;

import app.dto.UsuarioRequestDTO;
import app.dto.UsuarioResponseDTO;
import app.mapper.UsuarioMapper;
import app.model.Usuario;
import app.repository.UsuarioRepository;
import app.validation.UsuarioUpdateValidator;
import app.validation.UsuarioValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final List<UsuarioValidator> validators;
    private final List<UsuarioUpdateValidator> updateValidators;

    public UsuarioService(UsuarioRepository repository,
                          UsuarioMapper mapper,
                          List<UsuarioValidator> validators,
                          List<UsuarioUpdateValidator> updateValidators) {
        this.repository = repository;
        this.mapper = mapper;
        this.validators = validators;
        this.updateValidators = updateValidators;
    }

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        validators.forEach(v -> v.validar(dto));

        Usuario usuario = mapper.toEntity(dto);
        Usuario salvo = repository.save(usuario);

        return mapper.toResponseDTO(salvo);
    }

    public List<UsuarioResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id).orElse(null);

        if (usuario == null) {
            return null;
        }

        return mapper.toResponseDTO(usuario);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = repository.findById(id).orElse(null);

        if (usuario == null) {
            return null;
        }

        updateValidators.forEach(v -> v.validar(usuario, dto));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setNumero(dto.getNumero());
        usuario.setSenha(dto.getSenha());

        Usuario atualizado = repository.save(usuario);

        return mapper.toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}