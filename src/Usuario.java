public class Usuario {

    private String nome;
    private String email;
    private String numero;

    public Usuario(String nome, String email, String numero) {
        this.nome = nome;
        this.email = email;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void exibir() {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Numero: " + numero);
    }
}
