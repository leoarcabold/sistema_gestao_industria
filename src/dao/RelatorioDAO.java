package dao;

import config.ConexaoMySQL;
import model.Funcionario;
import model.Producao;
import model.Produto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {
    private static final Connection conn = ConexaoMySQL.getConnection();

    // 1. Listar funcionários por setor
    public List<Funcionario> listarFuncionariosPorSetor(int idSetor) {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionario WHERE id_setor = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSetor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getInt("id_funcionario"));
                f.setNome(rs.getString("nome"));
                f.setSobrenome(rs.getString("sobrenome"));
                lista.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários por setor: " + e.getMessage());
        }
        return lista;
    }

    // 2. Produções por data (data_producao é varchar)
    public List<Producao> listarProducaoPorData(String data) {
        List<Producao> lista = new ArrayList<>();
        String sql = "SELECT * FROM producao WHERE data_producao = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, data); // String direto
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Producao p = new Producao();
                p.setId(rs.getInt("id_producao"));
                p.setData(LocalDate.parse(rs.getString("data_producao"))); // usar String
                p.setQuantidade(rs.getInt("quantidade"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produção por data: " + e.getMessage());
        }
        return lista;
    }

    // 3. Produtos produzidos por funcionário
    public List<Produto> listarProdutosPorFuncionario(int idFuncionario) {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT p.* FROM producao pr " +
                "INNER JOIN produtos p ON pr.id_produtos = p.id_produtos " +
                "WHERE pr.id_funcionario = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setId(rs.getInt("id_produtos"));
                prod.setNome(rs.getString("nome_produto"));
                prod.setDescricao(rs.getString("descricao"));
                lista.add(prod);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos por funcionário: " + e.getMessage());
        }
        return lista;
    }

    // 4. Funcionários, setores e produções
    public List<String> listarFuncionariosSetoresProducoes() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT f.nome, s.nome_setor, p.quantidade, p.data_producao " +
                "FROM funcionario f " +
                "INNER JOIN setor s ON f.id_setor = s.id_setor " +
                "INNER JOIN producao p ON f.id_funcionario = p.id_funcionario";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String linha = rs.getString("nome") + " | " +
                        rs.getString("nome_setor") + " | " +
                        rs.getInt("quantidade") + " unidades em " +
                        rs.getString("data_producao");
                lista.add(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários + setores + produções: " + e.getMessage());
        }
        return lista;
    }

    // 5. Detalhes da produção em data específica
    public List<String> listarDetalhesProducaoPorData(String data) {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT f.nome, s.nome_setor, p.quantidade " +
                "FROM producao p " +
                "INNER JOIN funcionario f ON p.id_funcionario = f.id_funcionario " +
                "INNER JOIN setor s ON f.id_setor = s.id_setor " +
                "WHERE p.data_producao = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, data);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String linha = "Funcionário: " + rs.getString("nome") +
                        " | Setor: " + rs.getString("nome_setor") +
                        " | Quantidade: " + rs.getInt("quantidade");
                lista.add(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar detalhes da produção: " + e.getMessage());
        }
        return lista;
    }
}
