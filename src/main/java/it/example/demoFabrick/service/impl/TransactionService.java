package it.example.demoFabrick.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.example.demoFabrick.model.TransactionLog;
import it.example.demoFabrick.repository.TransactionRepository;
import it.example.demoFabrick.service.ITransactionService;

/**
 * Implementazione del servizio per la gestione delle transazioni.
 */
@Service
public class TransactionService implements ITransactionService{
	
	@Autowired
    private TransactionRepository transactionRepository;
	
	@Override
	public void saveTransaction(TransactionLog transaction) {
		transactionRepository.save(transaction);
	}
	
	@Override
	public List<TransactionLog> getAllTransactions() {
		return (List<TransactionLog>) transactionRepository.findAll();
	}

}
