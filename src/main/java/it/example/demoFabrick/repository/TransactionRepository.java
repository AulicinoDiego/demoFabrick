package it.example.demoFabrick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.example.demoFabrick.model.TransactionLog;

/**
 * Repository per l'entità TransactionLog.
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionLog, Long>{

}
