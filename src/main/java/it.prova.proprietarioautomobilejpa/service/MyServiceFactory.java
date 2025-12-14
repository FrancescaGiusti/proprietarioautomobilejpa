package it.prova.proprietarioautomobilejpa.service;

import it.prova.proprietarioautomobilejpa.dao.MyDaoFactory;
import it.prova.proprietarioautomobilejpa.model.Proprietario;
import it.prova.proprietarioautomobilejpa.service.automobile.AutomobileService;
import it.prova.proprietarioautomobilejpa.service.automobile.AutomobileServiceImpl;
import it.prova.proprietarioautomobilejpa.service.proprietario.ProprietarioService;
import it.prova.proprietarioautomobilejpa.service.proprietario.ProprietarioServiceImpl;

public class MyServiceFactory {

	private static AutomobileService automobileServiceInstance = null;
	private static ProprietarioService proprietarioServiceInstance = null;

	public static AutomobileService getAutomobileServiceInstance() {
		if (automobileServiceInstance == null) {
            automobileServiceInstance = new AutomobileServiceImpl();
            automobileServiceInstance.setAutomobileDAO(MyDaoFactory.getAutomobileDAOInstance());
		}
		return automobileServiceInstance;
	}

	public static ProprietarioService getProprietarioServiceInstance() {
		if (proprietarioServiceInstance == null) {
            proprietarioServiceInstance = new ProprietarioServiceImpl();
            proprietarioServiceInstance.setProprietarioDAO(MyDaoFactory.getProprietarioDAOInstance());
		}
		return proprietarioServiceInstance;
	}

}
