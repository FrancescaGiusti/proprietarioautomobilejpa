package it.prova.proprietarioautomobilejpa.service.automobile;

import it.prova.proprietarioautomobilejpa.dao.automobile.AutomobileDAO;
import it.prova.proprietarioautomobilejpa.model.Automobile;

import java.util.List;

public interface AutomobileService {
	
	public List<Automobile> listAllAutomobili() throws Exception;

	public Automobile caricaSingolaAutomobile(Long id) throws Exception;

	public void aggiorna(Automobile automobileInstance) throws Exception;

	public void inserisciNuovo(Automobile automobileInstance) throws Exception;

	public void rimuovi(Long idAutomobileInstance) throws Exception;

	public void setAutomobileDAO(AutomobileDAO automobileDAO);

}
