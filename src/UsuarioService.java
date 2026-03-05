import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    List<Usuario> usuarios = new ArrayList<>();

    public void cadastrar(String nome, String email, String numero, String senha) {
        Usuario usuario = new Usuario(nome, email, numero, senha);
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void consultar() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println("\nID: " + i);
                usuarios.get(i).exibir();
            }
        }
    }

    public void atualizar(int id, String nome, String email, String numero, String senha) {
        if (id >= 0 && id < usuarios.size()) {
            Usuario usuario = usuarios.get(id);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setNumero(numero);
            usuario.setSenha(senha);
            System.out.println("Usuário atualizado!");
        } else {
            System.out.println("ID inválido.");
        }
    }

    public void deletar(int id) {
        if (id >= 0 && id < usuarios.size()) {
            usuarios.remove(id);
            System.out.println("Usuário deletado!");
        } else {
            System.out.println("ID inválido.");
        }
    }
}