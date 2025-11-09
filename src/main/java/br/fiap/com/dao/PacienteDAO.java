package br.fiap.com.dao;

import br.fiap.com.to.PacienteTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteDAO {

    private Connection conn;

    public PacienteDAO() {
        this.conn = ConnectionFactory.getConnection();
    }


    public PacienteTO save(PacienteTO paciente) {
        String sql = "INSERT INTO DDD_PACIENTE (NOME, CPF, EMAIL, DT_NASCIMENTO, TIPO_DEFICIENCIA, ID_CUIDADOR) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getCpf());
            ps.setString(3, paciente.getEmail());
            ps.setDate(4, Date.valueOf(paciente.getDataNascimento()));
            ps.setString(5, paciente.getTipoDeficiencia());
            ps.setLong(6, paciente.getIdCuidador());

            if (ps.executeUpdate() > 0) {
                return paciente;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }


    public ArrayList<PacienteTO> findAll() {
        ArrayList<PacienteTO> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM DDD_PACIENTE ORDER BY NOME";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PacienteTO p = new PacienteTO();
                p.setIdPaciente(rs.getLong("ID_PACIENTE"));
                p.setNome(rs.getString("NOME"));
                p.setCpf(rs.getString("CPF"));
                p.setEmail(rs.getString("EMAIL"));
                p.setDataNascimento(rs.getDate("DT_NASCIMENTO").toLocalDate());
                p.setTipoDeficiencia(rs.getString("TIPO_DEFICIENCIA"));
                p.setIdCuidador(rs.getLong("ID_CUIDADOR"));
                pacientes.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pacientes;
    }

    public PacienteTO findByCodigo(Long codigo) {
        String sql = "SELECT * FROM DDD_PACIENTE WHERE ID_PACIENTE = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PacienteTO p = new PacienteTO();
                    p.setIdPaciente(rs.getLong("ID_PACIENTE"));
                    p.setNome(rs.getString("NOME"));
                    p.setCpf(rs.getString("CPF"));
                    p.setEmail(rs.getString("EMAIL"));
                    p.setDataNascimento(rs.getDate("DT_NASCIMENTO").toLocalDate());
                    p.setTipoDeficiencia(rs.getString("TIPO_DEFICIENCIA"));
                    p.setIdCuidador(rs.getLong("ID_CUIDADOR"));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public PacienteTO update(PacienteTO paciente) {
        String sql = "UPDATE DDD_PACIENTE SET NOME = ?, CPF = ?, EMAIL = ?, DT_NASCIMENTO = ?, TIPO_DEFICIENCIA = ?, ID_CUIDADOR = ? WHERE ID_PACIENTE = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getCpf());
            ps.setString(3, paciente.getEmail());
            ps.setDate(4, Date.valueOf(paciente.getDataNascimento()));
            ps.setString(5, paciente.getTipoDeficiencia());
            ps.setLong(6, paciente.getIdCuidador());
            ps.setLong(7, paciente.getIdPaciente());

            if (ps.executeUpdate() > 0) {
                return paciente;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long codigo) {
        String sql = "DELETE FROM DDD_PACIENTE WHERE ID_PACIENTE = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, codigo);

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }
}
