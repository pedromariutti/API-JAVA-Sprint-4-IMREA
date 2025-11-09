package br.fiap.com.bo;

import br.fiap.com.dao.CuidadorDAO;
import br.fiap.com.to.CuidadorTO;
import java.util.ArrayList;


public class CuidadorBO {

    private CuidadorDAO cuidadorDAO;

    public CuidadorTO save(CuidadorTO cuidador) {
        cuidadorDAO = new CuidadorDAO();

        if (cuidador.getEmail() != null) {
            cuidador.setEmail(cuidador.getEmail().toLowerCase());
        }
        return cuidadorDAO.save(cuidador);
    }

    public ArrayList<CuidadorTO> findAll() {
        cuidadorDAO = new CuidadorDAO();
        return cuidadorDAO.findAll();
    }

    public CuidadorTO findByCodigo(Long codigo) {
        cuidadorDAO = new CuidadorDAO();
        return cuidadorDAO.findByCodigo(codigo);
    }

    public CuidadorTO update(CuidadorTO cuidador) {
        cuidadorDAO = new CuidadorDAO();
        if (cuidador.getEmail() != null) {
            cuidador.setEmail(cuidador.getEmail().toLowerCase());
        }
        return cuidadorDAO.update(cuidador);
    }

    public boolean delete(Long codigo) {
        cuidadorDAO = new CuidadorDAO();
        return cuidadorDAO.delete(codigo);
    }
}
