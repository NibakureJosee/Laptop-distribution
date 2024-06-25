package rw.ne.laptopdistribution.services;

import rw.ne.laptopdistribution.dtos.BankingDTO;
import rw.ne.laptopdistribution.models.Banking;

import java.util.List;
import java.util.Optional;

public interface BankingService {

    Banking createBankingTransaction(BankingDTO bankingDTO);

    List<Banking> getAllBankingTransactions();

    Optional<Banking> getBankingTransactionById(Long id);

    void deleteBankingTransaction(Long id);
}
