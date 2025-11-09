package br.fiap.com.dao;

import br.fiap.com.to.CuidadorTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CuidadorDAO {

    private Connection conn;

    public CuidadorDAO() {
        this.conn = ConnectionFactory.getConnection();
    }


    public CuidadorTO save(CuidadorTO cuidador) {
        String sql = "INSERT INTO DDD_CUIDADOR (NOME, TELEFONE, EMAIL) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cuidador.getNome());
            ps.setString(2, cuidador.getTelefone());
            ps.setString(3, cuidador.getEmail());

            if (ps.executeUpdate() > 0) {
                return cuidador;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar cuidador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }


    public ArrayList<CuidadorTO> findAll() {
        ArrayList<CuidadorTO> cuidadores = new ArrayList<>();
        String sql = "SELECT * FROM DDD_CUIDADOR ORDER BY NOME";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CuidadorTO c = new CuidadorTO();
                c.setIdCuidador(rs.getLong("ID_CUIDADOR"));
                c.setNome(rs.getString("NOME"));
                c.setTelefone(rs.getString("TELEFONE"));
                c.setEmail(rs.getString("EMAIL"));
                cuidadores.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar cuidadores: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
        return cuidadores;
    }


    public CuidadorTO findByCodigo(Long codigo) {
        String sql = "SELECT * FROM DDD_CUIDADOR WHERE ID_CUIDADOR = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CuidadorTO c = new CuidadorTO();
                    c.setIdCuidador(rs.getLong("ID_CUIDADOR"));
                    c.setNome(rs.getString("NOME"));
                    c.setTelefone(rs.getString("TELEFONE"));
                    c.setEmail(rs.getString("EMAIL"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cuidador por código: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }


    public CuidadorTO update(CuidadorTO cuidador) {
        String sql = "UPDATE DDD_CUIDADOR SET NOME = ?, TELEFONE = ?, EMAIL = ? WHERE ID_CUIDADOR = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cuidador.getNome());
            ps.setString(2, cuidador.getTelefone());
            ps.setString(3, cuidador.getEmail());
            ps.setLong(4, cuidador.getIdCuidador());

            if (ps.executeUpdate() > 0) {
                return cuidador;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cuidador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }


    public boolean delete(Long codigo) {
        String sql = "DELETE FROM DDD_CUIDADOR WHERE ID_CUIDADOR = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar cuidador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }
}
