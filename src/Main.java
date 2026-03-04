import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String nome = "";
        String email = "";
        String numero = "";

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
                    System.out.println("Qual é o nome do usuario?");
                    nome = scanner.nextLine();

                    System.out.println("Qual é o email do usuario?");
                    email = scanner.nextLine();

                    System.out.println("Qual é o numero do usuario?");
                    numero = scanner.nextLine();

                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case 2:
                    if (nome.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        System.out.println("Nome: " + nome);
                        System.out.println("Email: " + email);
                        System.out.println("Numero: " + numero);
                    }
                    break;

                case 3:
                    if (nome.isEmpty()) {
                        System.out.println("Nenhum usuário para atualizar.");
                    } else {
                        System.out.println("Novo nome:");
                        nome = scanner.nextLine();

                        System.out.println("Novo email:");
                        email = scanner.nextLine();

                        System.out.println("Novo numero:");
                        numero = scanner.nextLine();

                        System.out.println("Usuário atualizado!");
                    }
                    break;

                case 4:
                    nome = "";
                    email = "";
                    numero = "";
                    System.out.println("Usuário deletado!");
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