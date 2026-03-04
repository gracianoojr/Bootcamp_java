public class UsuarioService {

    private Usuario usuario;

    public void cadastrar(String nome, String email, String numero) {
        usuario = new Usuario(nome, email, numero);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void consultar() {
        if (usuario == null) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            usuario.exibir();
        }
    }

    public void atualizar(String nome, String email, String numero) {
        if (usuario == null) {
            System.out.println("Nenhum usuário para atualizar.");
        } else {
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setNumero(numero);
            System.out.println("Usuário atualizado!");
        }
    }

    public void deletar() {
        if (usuario == null) {
            System.out.println("Nenhum usuário para deletar.");
        } else {
            usuario = null;
            System.out.println("Usuário deletado!");
        }
    }
}