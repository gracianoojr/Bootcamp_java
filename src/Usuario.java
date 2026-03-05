public class Usuario {
    String nome;
    String email;
    String numero;
    String senha;

    public Usuario(String nome, String email, String numero, String senha) {
        this.nome = nome;
        this.email = email;
        this.numero = numero;
        this.senha = senha;
    }

    public String getNome() {return nome;}

    public String getSenha() {return senha;}

    public String getEmail() {return email;}

    public String getNumero() {return numero;}

    public void setNome(String nome) {this.nome = nome;}

    public void setSenha(String senha) {this.senha = senha;}

    public void setEmail(String email) {this.email = email;}

    public void setNumero(String numero) {this.numero = numero;}

    public void exibir() {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Numero: " + numero);
    }
}
