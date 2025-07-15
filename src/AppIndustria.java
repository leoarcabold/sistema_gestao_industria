import dao.RelatorioDAO;
import file.EscritaArquivo;
import menu.FuncionarioMenu;
import menu.ProducaoMenu;
import menu.ProdutoMenu;
import menu.SetorMenu;
import model.Relatorio;

import java.util.Scanner;

public class AppIndustria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=====MENU PRINCIPAL=====");
            System.out.println("[1] Produção");
            System.out.println("[2] Funcionário");
            System.out.println("[3] Setor");
            System.out.println("[4] Produto");
            System.out.println("[5] Relatório");
            System.out.println("[0] Sair");
            System.out.print("Opção: ");
            Integer opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    ProducaoMenu.main(args);
                    break;
                case 2:
                    FuncionarioMenu.main(args);
                    break;
                case 3:
                    SetorMenu.main(args);
                    break;
                case 4:
                    ProdutoMenu.main(args);
                    break;
                case 5:
                    Relatorio relatorio = new Relatorio(new RelatorioDAO());
                    System.out.println(relatorio.gerarRelatorioGeral());
                    EscritaArquivo escritaArquivo = new EscritaArquivo();
                    escritaArquivo.salvarCSV(relatorio.gerarRelatorioGeral());
                    break;
                case 0:
                    System.out.println("Programa finalizado com sucesso.");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
