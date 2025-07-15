package dao;

import config.ConexaoMySQL;
import model.Funcionario;
import model.Producao;
import model.Produto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProducaoDAO {
    private Connection conn = ConexaoMySQL.getConnection();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public ArrayList<Producao> listar() {
        ArrayList<Producao> producoes = new ArrayList<>();
        String sql = "SELECT * FROM producao;";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Integer id = rs.getInt("id_producao");
                Integer idProduto = rs.getInt("id_produto");
                Integer idFuncionario = rs.getInt("id_funcionario");
                LocalDate data = rs.getDate("data").toLocalDate();
                Integer quantidade = rs.getInt("quantidade");

                Produto produto = produtoDAO.buscarPorId(idProduto);
                Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);

                Producao producao = new Producao(id, produto, funcionario, data, quantidade);
                producoes.add(producao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produções: " + e.getMessage());
        }
        return producoes;
    }

    public Producao buscarPorId(Integer id) {
        String sql = "SELECT * FROM producao WHERE id_producao = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer idProduto = rs.getInt("id_produto");
                Integer idFuncionario = rs.getInt("id_funcionario");
                LocalDate data = rs.getDate("data").toLocalDate();
                Integer quantidade = rs.getInt("quantidade");

                Produto produto = produtoDAO.buscarPorId(idProduto);
                Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);

                return new Producao(id, produto, funcionario, data, quantidade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produção por ID: " + e.getMessage());
        }
        return null;
    }

    public Boolean cadastrar(Producao producao) {
        String sql = "INSERT INTO producao (id_produto, id_funcionario, data, quantidade) VALUES (?, ?, ?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, producao.getProduto().getId());
//            ps.setInt(2, producao.getFuncionario().getId());
            ps.setDate(3, Date.valueOf(producao.getData()));
            ps.setInt(4, producao.getQuantidade());
            int linhas = ps.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produção: " + e.getMessage());
        }
        return false;
    }

    public Boolean atualizar(Producao producao) {
        String sql = "UPDATE producao SET id_produto = ?, id_funcionario = ?, data = ?, quantidade = ? WHERE id_producao = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, producao.getProduto().getId());
//            ps.setInt(2, producao.getFuncionario().getId());
            ps.setDate(3, Date.valueOf(producao.getData()));
            ps.setInt(4, producao.getQuantidade());
            ps.setInt(5, producao.getId());
            int linhas = ps.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produção: " + e.getMessage());
        }
        return false;
    }

    public Boolean remover(Integer id) {
        String sql = "DELETE FROM producao WHERE id_producao = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao remover produção: " + e.getMessage());
        }
        return false;
    }
}
