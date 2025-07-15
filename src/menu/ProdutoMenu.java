package menu;

import dao.ProdutoDAO;
import model.Produto;

import java.util.ArrayList;
import java.util.Scanner;

public class ProdutoMenu {

    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== MENU PRODUTO ===");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Buscar produto por ID");
            System.out.println("3 - Cadastrar produto");
            System.out.println("4 - Atualizar produto");
            System.out.println("5 - Remover produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    buscarProdutoPorId();
                    break;
                case 3:
                    cadastrarProduto();
                    break;
                case 4:
                    atualizarProduto();
                    break;
                case 5:
                    removerProduto();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listarProdutos() {
        ArrayList<Produto> produtos = produtoDAO.listar();
        if (produtos != null && !produtos.isEmpty()) {
            System.out.println("\nLista de Produtos:");
            for (Produto p : produtos) {
                System.out.println(p);
            }
        } else {
            System.out.println("Nenhum produto encontrado.");
        }
    }

    private static void buscarProdutoPorId() {
        System.out.print("Digite o ID do produto: ");
        int id = Integer.parseInt(scanner.nextLine());
        Produto produto = produtoDAO.buscarPorId(id);
        if (produto != null) {
            System.out.println(produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição do produto: ");
        String descricao = scanner.nextLine();

        Produto produto = new Produto(null, nome, descricao);
        if (produtoDAO.cadastrar(produto)) {
            System.out.println("Produto cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar produto.");
        }
    }

    private static void atualizarProduto() {
        System.out.print("ID do produto a atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Produto produtoExistente = produtoDAO.buscarPorId(id);
        if (produtoExistente == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova descrição: ");
        String descricao = scanner.nextLine();

        Produto produto = new Produto(id, nome, descricao);
        if (produtoDAO.atualizar(produto)) {
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar produto.");
        }
    }

    private static void removerProduto() {
        System.out.print("ID do produto a remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (produtoDAO.remover(id)) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Erro ao remover produto.");
        }
    }
}
