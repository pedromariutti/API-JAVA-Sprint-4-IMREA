package br.fiap.com.bo;

import br.fiap.com.dao.PacienteDAO;
import br.fiap.com.to.PacienteTO;
import java.util.ArrayList;

public class PacienteBO {

    private PacienteDAO pacienteDAO;

    public PacienteTO save(PacienteTO paciente) {
        pacienteDAO = new PacienteDAO();
        return pacienteDAO.save(paciente);
    }

    public ArrayList<PacienteTO> findAll() {
        pacienteDAO = new PacienteDAO();
        return pacienteDAO.findAll();
    }

    public PacienteTO findByCodigo(Long codigo) {
        pacienteDAO = new PacienteDAO();
        return pacienteDAO.findByCodigo(codigo);
    }

    public PacienteTO update(PacienteTO paciente) {
        pacienteDAO = new PacienteDAO();
        return pacienteDAO.update(paciente);
    }

    public boolean delete(Long codigo) {
        pacienteDAO = new PacienteDAO();
        return pacienteDAO.delete(codigo);
    }
}
