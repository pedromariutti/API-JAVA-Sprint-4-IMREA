package br.fiap.com.bo;

import br.fiap.com.dao.ConsultaDAO;
import br.fiap.com.to.ConsultaTO;
import java.util.ArrayList;

public class ConsultaBO {

    private ConsultaDAO consultaDAO;

    public ConsultaTO save(ConsultaTO consulta) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.save(consulta);
    }

    public ArrayList<ConsultaTO> findAll() {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.findAll();
    }

    public ConsultaTO findByCodigo(Long codigo) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.findByCodigo(codigo);
    }

    public ConsultaTO update(ConsultaTO consulta) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.update(consulta);
    }

    public boolean delete(Long codigo) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.delete(codigo);
    }
}
