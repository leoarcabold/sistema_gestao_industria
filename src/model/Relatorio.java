package model;

import dao.RelatorioDAO;

import java.util.List;

public class Relatorio {
    private final RelatorioDAO dao;

    public Relatorio(RelatorioDAO dao) {
        this.dao = dao;
    }

    public String gerarRelatorioGeral() {
        StringBuilder sb = new StringBuilder();
        sb.append("====== RELATÓRIO GERAL DA INDÚSTRIA ======\n\n");

        sb.append("🔹 Funcionários do Setor 1:\n");
        List<Funcionario> funcionarios = dao.listarFuncionariosPorSetor(1);
        funcionarios.forEach(f -> sb.append("   ").append(f).append("\n"));
        sb.append("\n");

        sb.append("🔹 Produções em 2025-06-20:\n");
        List<Producao> producoes = dao.listarProducaoPorData("2025-06-20");
        producoes.forEach(p -> sb.append("   ").append(p).append("\n"));
        sb.append("\n");

        sb.append("🔹 Produtos produzidos pelo Funcionário ID 1:\n");
        List<Produto> produtos = dao.listarProdutosPorFuncionario(1);
        produtos.forEach(prod -> sb.append("   ").append(prod).append("\n"));
        sb.append("\n");

        sb.append("🔹 Funcionários, Setores e Produções:\n");
        List<String> registros = dao.listarFuncionariosSetoresProducoes();
        registros.forEach(linha -> sb.append("   ").append(linha).append("\n"));
        sb.append("\n");

        sb.append("🔹 Detalhes da produção em 2025-06-20:\n");
        List<String> detalhes = dao.listarDetalhesProducaoPorData("2025-06-20");
        detalhes.forEach(linha -> sb.append("   ").append(linha).append("\n"));
        sb.append("\n");

        sb.append("====== FIM DO RELATÓRIO ======\n");
        return sb.toString();
    }
}
