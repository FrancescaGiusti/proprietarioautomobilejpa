package it.prova.proprietarioautomobilejpa.service.proprietario;

import it.prova.proprietarioautomobilejpa.dao.proprietario.ProprietarioDAO;
import it.prova.proprietarioautomobilejpa.model.Proprietario;

import java.util.List;

public interface ProprietarioService {
	
	public List<Proprietario> listAllProprietari() throws Exception;

	public Proprietario caricaSingoloProprietario(Long id) throws Exception;
	
	public Proprietario caricaSingoloProprietarioConAutomobili(Long id) throws Exception;

	public void aggiorna(Proprietario proprietarioInstance) throws Exception;

	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception;

	public void rimuovi(Proprietario proprietarioInstance) throws Exception;

	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO);
}
