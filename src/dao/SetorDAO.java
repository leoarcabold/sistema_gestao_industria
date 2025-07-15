package dao;

import config.ConexaoMySQL;
import model.Setor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SetorDAO {
    private Connection conn = ConexaoMySQL.getConnection();

    public ArrayList<Setor> listar() {
        try {
            ArrayList<Setor> setores = new ArrayList<>();
            String sql = "SELECT * FROM setor;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_setor");
                String nome = rs.getString("nome_setor");
                String responsavel = rs.getString("responsavel");

                Setor setor = new Setor(id, nome, responsavel);

                setores.add(setor);
            }
            return setores;

        } catch (SQLException e) {
            System.out.println("Deu ruim em listar os clientes. " + e.getMessage());
            return null;
        }
    }

    public Setor buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM setor WHERE id_setor = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Integer idSetor = rs.getInt("id_setor");
                String nome = rs.getString("nome_setor");
                String responsavel = rs.getString("responsavel");
                Setor setor = new Setor(idSetor, nome, responsavel);
                return setor;
            }
            return null;


        } catch (SQLException e) {
            System.out.println("Erro ao buscar o setor pelo ID. " + e.getMessage());
        }

        return null;
    }

    public Boolean cadastrar(Setor setor) {
        try {
            String sql = "INSERT INTO setor (nome_setor, responsavel) VALUES(?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, setor.getNome());
            ps.setString(2, setor.getResponsavel());
            int qtdeLinha = ps.executeUpdate();

            if (qtdeLinha > 0) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar setor. " + e.getMessage());
        }


        return false;
    }

    public Boolean atualizar(Setor setor) {
        try {
            String sql = "UPDATE setor SET nome_setor = ?, responsavel = ? WHERE id_setor = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, setor.getNome());
            ps.setString(2, setor.getResponsavel());
            ps.setInt(3, setor.getIdSetor());
            int qtdeAtualizacoes = ps.executeUpdate();

            if (qtdeAtualizacoes > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o setor." + e.getMessage());
        }
        return false;
    }

    public Boolean remover(Integer id) {
        try {
            String sql = "DELETE FROM setor WHERE id_setor = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            Setor setorRetornado = buscarPorId(id);

            if (setorRetornado != null) {
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o setor." + e.getMessage());
        }

        return false;
    }
}
