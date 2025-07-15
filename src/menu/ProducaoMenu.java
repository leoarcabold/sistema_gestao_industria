package menu;

import dao.ProducaoDAO;
import dao.ProdutoDAO;
import dao.FuncionarioDAO;
import model.Producao;
import model.Produto;
import model.Funcionario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ProducaoMenu {

    private static ProducaoDAO producaoDAO = new ProducaoDAO();
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== MENU PRODUÇÃO ===");
            System.out.println("1 - Listar produções");
            System.out.println("2 - Buscar produção por ID");
            System.out.println("3 - Cadastrar produção");
            System.out.println("4 - Atualizar produção");
            System.out.println("5 - Remover produção");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    listarProducoes();
                    break;
                case 2:
                    buscarProducaoPorId();
                    break;
                case 3:
                    cadastrarProducao();
                    break;
                case 4:
                    atualizarProducao();
                    break;
                case 5:
                    removerProducao();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listarProducoes() {
        ArrayList<Producao> producoes = producaoDAO.listar();
        if (producoes != null && !producoes.isEmpty()) {
            System.out.println("\nLista de Produções:");
            for (Producao p : producoes) {
                System.out.println(p);
            }
        } else {
            System.out.println("Nenhuma produção encontrada.");
        }
    }

    private static void buscarProducaoPorId() {
        System.out.print("Digite o ID da produção: ");
        int id = Integer.parseInt(scanner.nextLine());
        Producao producao = producaoDAO.buscarPorId(id);
        if (producao != null) {
            System.out.println(producao);
        } else {
            System.out.println("Produção não encontrada.");
        }
    }

    private static void cadastrarProducao() {
        System.out.print("ID do produto: ");
        int idProduto = Integer.parseInt(scanner.nextLine());
        Produto produto = produtoDAO.buscarPorId(idProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado. Cadastro cancelado.");
            return;
        }

        System.out.print("ID do funcionário: ");
        int idFuncionario = Integer.parseInt(scanner.nextLine());
        Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado. Cadastro cancelado.");
            return;
        }

        System.out.print("Data da produção (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());

        System.out.print("Quantidade produzida: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        Producao producao = new Producao(null, produto, funcionario, data, quantidade);
        if (producaoDAO.cadastrar(producao)) {
            System.out.println("Produção cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar produção.");
        }
    }

    private static void atualizarProducao() {
        System.out.print("ID da produção a atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Producao producaoExistente = producaoDAO.buscarPorId(id);
        if (producaoExistente == null) {
            System.out.println("Produção não encontrada.");
            return;
        }

        System.out.print("Novo ID do produto: ");
        int idProduto = Integer.parseInt(scanner.nextLine());
        Produto produto = produtoDAO.buscarPorId(idProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado. Atualização cancelada.");
            return;
        }

        System.out.print("Novo ID do funcionário: ");
        int idFuncionario = Integer.parseInt(scanner.nextLine());
        Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado. Atualização cancelada.");
            return;
        }

        System.out.print("Nova data da produção (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());

        System.out.print("Nova quantidade produzida: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        Producao producao = new Producao(id, produto, funcionario, data, quantidade);
        if (producaoDAO.atualizar(producao)) {
            System.out.println("Produção atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar produção.");
        }
    }

    private static void removerProducao() {
        System.out.print("ID da produção a remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (producaoDAO.remover(id)) {
            System.out.println("Produção removida com sucesso!");
        } else {
            System.out.println("Erro ao remover produção.");
        }
    }
}
