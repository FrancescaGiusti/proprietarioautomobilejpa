package it.prova.proprietarioautomobilejpa.dao.proprietario;

import it.prova.proprietarioautomobilejpa.dao.IBaseDAO;
import it.prova.proprietarioautomobilejpa.model.Proprietario;

public interface ProprietarioDAO extends IBaseDAO<Proprietario> {
    Proprietario getEagerAutomobili(Long id) throws Exception;
}
