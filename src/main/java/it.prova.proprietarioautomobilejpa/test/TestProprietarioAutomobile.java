package it.prova.proprietarioautomobilejpa.test;

import it.prova.proprietarioautomobilejpa.dao.EntityManagerUtil;
import it.prova.proprietarioautomobilejpa.model.Automobile;
import it.prova.proprietarioautomobilejpa.model.Proprietario;
import it.prova.proprietarioautomobilejpa.service.MyServiceFactory;
import it.prova.proprietarioautomobilejpa.service.automobile.AutomobileService;
import it.prova.proprietarioautomobilejpa.service.proprietario.ProprietarioService;
import org.hibernate.LazyInitializationException;

import java.time.LocalDate;
import java.util.List;

public class TestProprietarioAutomobile {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {

			System.out.println(
					"In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size() + " elementi.");

			testInserisciProprietario(proprietarioService);
            System.out.println(
                    "In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size() + " elementi.");

			testInserisciAutomobile(proprietarioService, automobileService);
            System.out.println(
                    "In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size() + " elementi.");

			testRimozioneAutomobile(proprietarioService, automobileService);
            System.out.println(
                    "In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size() + " elementi.");

            testLazyInitExc(proprietarioService, automobileService);

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testInserisciProprietario inizio.............");
        Proprietario nuovoProprietario = new Proprietario("Carlo", "Conti", LocalDate.of(1990, 6, 12), "CHAFTR632634U");
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		proprietarioService.inserisciNuovo(nuovoProprietario);
		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("testInserisciProprietario fallito ");

		System.out.println(".......testInserisciProprietario fine: PASSED.............");
	}

	private static void testInserisciAutomobile(ProprietarioService proprietarioService, AutomobileService automobileService)
			throws Exception {
		System.out.println(".......testInserisciAutomobile inizio.............");

		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testInserisciAutomobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovaAutomobile = new Automobile("Toyota", "gs556", "53682GAV", LocalDate.of(1990, 6, 12));
		nuovaAutomobile.setProprietario(listaProprietariPresenti.get(0));

		automobileService.inserisciNuovo(nuovaAutomobile);

		if (nuovaAutomobile.getId() == null)
			throw new RuntimeException("testInserisciAutomobile fallito ");

		if (nuovaAutomobile.getProprietario() == null)
			throw new RuntimeException("testInserisciAutomobile fallito: non ha collegato il proprietario ");

		System.out.println(".......testInserisciAutomobile fine: PASSED.............");
	}

	private static void testRimozioneAutomobile(ProprietarioService proprietarioService, AutomobileService automobileService)
			throws Exception {
		System.out.println(".......testRimozioneAutomobile inizio.............");

        List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
        if (listaProprietariPresenti.isEmpty())
            throw new RuntimeException("testRimozioneAutomobile fallito: non ci sono proprietari a cui collegarci ");

        Automobile nuovaAutomobile = new Automobile("Toyota", "gs556", "53682GAV", LocalDate.of(2025, 01, 01));
        nuovaAutomobile.setProprietario(listaProprietariPresenti.get(0));

        automobileService.inserisciNuovo(nuovaAutomobile);

		Long idAutomobileInserita = nuovaAutomobile.getId();
		automobileService.rimuovi(idAutomobileInserita);

		if (automobileService.caricaSingolaAutomobile(idAutomobileInserita) != null)
			throw new RuntimeException("testRimozioneAutomobile fallito: record non cancellato ");
		System.out.println(".......testRimozioneAutomobile fine: PASSED.............");
	}

	private static void testLazyInitExc(ProprietarioService proprietarioService, AutomobileService automobileService)
			throws Exception {
		System.out.println(".......testLazyInitExc inizio.............");


        List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
        if (listaProprietariPresenti.isEmpty())
            throw new RuntimeException("testLazyInitExc fallito: non ci sono proprietari a cui collegarci ");

		Proprietario proprietarioSuCuiFareIlTest = listaProprietariPresenti.get(0);

		try {
			proprietarioSuCuiFareIlTest.getAutomobili().size();

			throw new RuntimeException("testLazyInitExc fallito: eccezione non lanciata ");
		} catch (LazyInitializationException e) {
		}
		System.out.println(".......testLazyInitExc fine: PASSED.............");
	}

}
