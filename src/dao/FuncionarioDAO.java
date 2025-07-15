package dao;

import config.ConexaoMySQL;
import model.Funcionario;
import model.Setor;

import java.sql.*;
import java.util.ArrayList;

public class FuncionarioDAO {
    private Connection conn = ConexaoMySQL.getConnection();
    private SetorDAO setorDAO = new SetorDAO();

    public ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario;";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Integer id = rs.getInt("id_funcionario");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                Integer idSetor = rs.getInt("id_setor");
                Setor setor = setorDAO.buscarPorId(idSetor);

                Funcionario funcionario = new Funcionario(id, nome, sobrenome, setor);
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }
        return funcionarios;
    }

    public Funcionario buscarPorId(Integer id) {
        String sql = "SELECT * FROM funcionario WHERE id_funcionario = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                Integer idSetor = rs.getInt("id_setor");
                Setor setor = setorDAO.buscarPorId(idSetor);

                return new Funcionario(id, nome, sobrenome, setor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário por ID: " + e.getMessage());
        }
        return null;
    }

    public Boolean cadastrar(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, sobrenome, id_setor) VALUES (?, ?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getSobrenome());
            ps.setInt(3, funcionario.getSetor().getIdSetor());
            int linhas = ps.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
        return false;
    }

    public Boolean atualizar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome = ?, sobrenome = ?, id_setor = ? WHERE id_funcionario = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getSobrenome());
            ps.setInt(3, funcionario.getSetor().getIdSetor());
            ps.setInt(4, funcionario.getIdFuncionario());
            int linhas = ps.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
        return false;
    }

    public Boolean remover(Integer id) {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao remover funcionário: " + e.getMessage());
        }
        return false;
    }
}
