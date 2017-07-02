package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.libretto.db.EsameDAO;

public class Model {
	
	private List<Esame> esami ;

	public Model() {
		this.esami = new ArrayList<Esame>() ;
	}

	/*
	 * Aggiunge un nuovo esame all'elenco degli esami presenti
	 * verificando che non ci sia già
	 * return true se l'ha inserito, false se già esisteva
	 */
	public boolean addEsame (Esame e) {
		
		EsameDAO dao = new EsameDAO();
		
		return dao.create(e);
	}
	
	/*
	 * Ricerca se esiste un esame con il codice specificato
	 * Se esiste, lo restituisce, altrimenti restituisce null
	 * @param codice codice dell'esame da ricercare
	 * @return l'esame trovato, oppure null se non trovato
	 */
	public Esame trovaEsame(String codice) {
		EsameDAO dao = new EsameDAO();
		
		Esame e = dao.find(codice);
		
		return e;
	}
	
}
