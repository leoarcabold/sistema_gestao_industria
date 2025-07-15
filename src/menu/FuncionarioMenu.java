package menu;

import dao.FuncionarioDAO;
import dao.SetorDAO;
import model.Funcionario;
import model.Setor;

import java.util.ArrayList;
import java.util.Scanner;

public class FuncionarioMenu {

    private static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private static SetorDAO setorDAO = new SetorDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== MENU FUNCIONÁRIO ===");
            System.out.println("1 - Listar funcionários");
            System.out.println("2 - Buscar funcionário por ID");
            System.out.println("3 - Cadastrar funcionário");
            System.out.println("4 - Atualizar funcionário");
            System.out.println("5 - Remover funcionário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    listarFuncionarios();
                    break;
                case 2:
                    buscarFuncionarioPorId();
                    break;
                case 3:
                    cadastrarFuncionario();
                    break;
                case 4:
                    atualizarFuncionario();
                    break;
                case 5:
                    removerFuncionario();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listarFuncionarios() {
        ArrayList<Funcionario> funcionarios = funcionarioDAO.listar();
        if (funcionarios != null && !funcionarios.isEmpty()) {
            System.out.println("\nLista de Funcionários:");
            for (Funcionario f : funcionarios) {
                System.out.println(f);
            }
        } else {
            System.out.println("Nenhum funcionário encontrado.");
        }
    }

    private static void buscarFuncionarioPorId() {
        System.out.print("Digite o ID do funcionário: ");
        int id = Integer.parseInt(scanner.nextLine());
        Funcionario funcionario = funcionarioDAO.buscarPorId(id);
        if (funcionario != null) {
            System.out.println(funcionario);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static void cadastrarFuncionario() {
        System.out.print("Nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome do funcionário: ");
        String sobrenome = scanner.nextLine();
        System.out.print("ID do setor: ");
        int idSetor = Integer.parseInt(scanner.nextLine());
        Setor setor = setorDAO.buscarPorId(idSetor);

        if (setor == null) {
            System.out.println("Setor não encontrado. Cadastro cancelado.");
            return;
        }

        Funcionario funcionario = new Funcionario(null, nome, sobrenome, setor);
        if (funcionarioDAO.cadastrar(funcionario)) {
            System.out.println("Funcionário cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar funcionário.");
        }
    }

    private static void atualizarFuncionario() {
        System.out.print("ID do funcionário a atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Funcionario funcionarioExistente = funcionarioDAO.buscarPorId(id);
        if (funcionarioExistente == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo sobrenome: ");
        String sobrenome = scanner.nextLine();
        System.out.print("Novo ID do setor: ");
        int idSetor = Integer.parseInt(scanner.nextLine());
        Setor setor = setorDAO.buscarPorId(idSetor);

        if (setor == null) {
            System.out.println("Setor não encontrado. Atualização cancelada.");
            return;
        }

        Funcionario funcionario = new Funcionario(id, nome, sobrenome, setor);
        if (funcionarioDAO.atualizar(funcionario)) {
            System.out.println("Funcionário atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar funcionário.");
        }
    }

    private static void removerFuncionario() {
        System.out.print("ID do funcionário a remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (funcionarioDAO.remover(id)) {
            System.out.println("Funcionário removido com sucesso!");
        } else {
            System.out.println("Erro ao remover funcionário.");
        }
    }
}
