package menu;

import dao.SetorDAO;
import model.Setor;

import java.util.Scanner;
import java.util.ArrayList;

public class SetorMenu {

    private static SetorDAO dao = new SetorDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU SETOR ---");
            System.out.println("[1] Listar setores");
            System.out.println("[2] Buscar setor por ID");
            System.out.println("[3] Cadastrar setor");
            System.out.println("[4] Atualizar setor");
            System.out.println("[5] Remover setor");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    listarSetores();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    cadastrarSetor();
                    break;
                case 4:
                    atualizarSetor();
                    break;
                case 5:
                    removerSetor();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listarSetores() {
        ArrayList<Setor> setores = dao.listar();
        if (setores != null && !setores.isEmpty()) {
            for (Setor setor : setores) {
                System.out.println(setor);
            }
        } else {
            System.out.println("Nenhum setor encontrado.");
        }
    }

    private static void buscarPorId() {
        System.out.print("Digite o ID do setor: ");
        int id = scanner.nextInt();
        Setor setor = dao.buscarPorId(id);
        if (setor != null) {
            System.out.println(setor);
        } else {
            System.out.println("model.Setor não encontrado.");
        }
    }

    private static void cadastrarSetor() {
        System.out.print("Nome do setor: ");
        String nome = scanner.nextLine();
        System.out.print("Responsável: ");
        String responsavel = scanner.nextLine();
        Setor setor = new Setor(null, nome, responsavel);
        if (dao.cadastrar(setor)) {
            System.out.println("model.Setor cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar setor.");
        }
    }

    private static void atualizarSetor() {
        System.out.print("ID do setor a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        Setor setor = dao.buscarPorId(id);
        if (setor == null) {
            System.out.println("model.Setor não encontrado.");
            return;
        }
        System.out.print("Novo nome do setor: ");
        String nome = scanner.nextLine();
        System.out.print("Novo responsável: ");
        String responsavel = scanner.nextLine();
        setor.setNome(nome);
        setor.setResponsavel(responsavel);
        if (dao.atualizar(setor)) {
            System.out.println("model.Setor atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar setor.");
        }
    }

    private static void removerSetor() {
        System.out.print("ID do setor a remover: ");
        int id = scanner.nextInt();
        if (dao.remover(id)) {
            System.out.println("model.Setor removido com sucesso!");
        } else {
            System.out.println("Erro ao remover setor.");
        }
    }
}
