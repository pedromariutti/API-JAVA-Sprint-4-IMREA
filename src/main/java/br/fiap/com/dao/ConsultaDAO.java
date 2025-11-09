package br.fiap.com.dao;

import br.fiap.com.to.ConsultaTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaDAO {

    private Connection conn;

    public ConsultaDAO() {
        this.conn = ConnectionFactory.getConnection();
    }

    public ConsultaTO save(ConsultaTO consulta) {
        String sql = "INSERT INTO DDD_CONSULTA (ESPECIALIDADE, DT_CONSULTA, DETALHES, ID_PACIENTE) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, consulta.getEspecialidade());
            ps.setDate(2, Date.valueOf(consulta.getDataConsulta()));
            ps.setString(3, consulta.getDetalhes());
            ps.setLong(4, consulta.getIdPaciente());

            if (ps.executeUpdate() > 0) {
                return consulta;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<ConsultaTO> findAll() {
        ArrayList<ConsultaTO> consultas = new ArrayList<>();
        String sql = "SELECT * FROM DDD_CONSULTA ORDER BY DT_CONSULTA DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ConsultaTO c = new ConsultaTO();
                c.setIdConsulta(rs.getLong("ID_CONSULTA"));
                c.setEspecialidade(rs.getString("ESPECIALIDADE"));
                c.setDataConsulta(rs.getDate("DT_CONSULTA").toLocalDate());
                c.setDetalhes(rs.getString("DETALHES"));
                c.setIdPaciente(rs.getLong("ID_PACIENTE"));
                consultas.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
        return consultas;
    }

    public ConsultaTO findByCodigo(Long codigo) {
        String sql = "SELECT * FROM DDD_CONSULTA WHERE ID_CONSULTA = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ConsultaTO c = new ConsultaTO();
                    c.setIdConsulta(rs.getLong("ID_CONSULTA"));
                    c.setEspecialidade(rs.getString("ESPECIALIDADE"));
                    c.setDataConsulta(rs.getDate("DT_CONSULTA").toLocalDate());
                    c.setDetalhes(rs.getString("DETALHES"));
                    c.setIdPaciente(rs.getLong("ID_PACIENTE"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ConsultaTO update(ConsultaTO consulta) {
        String sql = "UPDATE DDD_CONSULTA SET ESPECIALIDADE = ?, DT_CONSULTA = ?, DETALHES = ?, ID_PACIENTE = ? WHERE ID_CONSULTA = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, consulta.getEspecialidade());
            ps.setDate(2, Date.valueOf(consulta.getDataConsulta()));
            ps.setString(3, consulta.getDetalhes());
            ps.setLong(4, consulta.getIdPaciente());
            ps.setLong(5, consulta.getIdConsulta());

            if (ps.executeUpdate() > 0) {
                return consulta;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long codigo) {
        String sql = "DELETE FROM DDD_CONSULTA WHERE ID_CONSULTA = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }
}