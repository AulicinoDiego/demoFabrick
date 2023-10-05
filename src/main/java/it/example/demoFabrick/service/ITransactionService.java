package it.example.demoFabrick.service;

import java.util.List;

import it.example.demoFabrick.model.TransactionLog;

/**
 * Interfaccia per il servizio di gestione delle transazioni.
 */
public interface ITransactionService {
	
	/**
     * Salva una transazione nel database.
     *
     * @param transaction Transazione da salvare
     */
	public void saveTransaction(TransactionLog transaction);
	
	/**
     * Ottiene tutte le transazioni salvate nel database.
     *
     * @return Lista delle transazioni
     */
	public List<TransactionLog> getAllTransactions();

}
