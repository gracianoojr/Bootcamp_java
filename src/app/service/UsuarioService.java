package app.service;

import app.model.Usuario;
import app.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrar(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Usuario atualizar(Long id, Usuario novoUsuario) {
        Usuario usuario = repository.findById(id).orElse(null);

        if (usuario != null) {
            usuario.setNome(novoUsuario.getNome());
            usuario.setEmail(novoUsuario.getEmail());
            usuario.setNumero(novoUsuario.getNumero());
            usuario.setSenha(novoUsuario.getSenha());

            return repository.save(usuario);
        }

        return null;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}