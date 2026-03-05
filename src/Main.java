import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UsuarioService service = new UsuarioService();
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.println("Nome:");
                    String nome = scanner.nextLine();

                    System.out.println("Email:");
                    String email = scanner.nextLine();

                    System.out.println("Numero:");
                    String numero = scanner.nextLine();

                    System.out.println("Senha:");
                    String senha = scanner.nextLine();

                    service.cadastrar(nome, email, numero, senha);
                    break;

                case 2:
                    service.consultar();
                    break;

                case 3:
                    System.out.println("Digite o ID do usuário:");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Novo nome:");
                    String nomeAtualizado = scanner.nextLine();

                    System.out.println("Novo email:");
                    String emailAtualizado = scanner.nextLine();

                    System.out.println("Novo numero:");
                    String numeroAtualizado = scanner.nextLine();

                    System.out.println("Nova senha:");
                    String novaSenha = scanner.nextLine();

                    service.atualizar(idAtualizar, nomeAtualizado, emailAtualizado, numeroAtualizado, novaSenha);
                    break;

                case 4:
                    System.out.println("Digite o ID do usuário:");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine();

                    service.deletar(idDeletar);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}