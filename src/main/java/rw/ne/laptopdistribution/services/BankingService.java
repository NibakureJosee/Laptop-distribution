package rw.ne.laptopdistribution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ne.laptopdistribution.dtos.BankingDTO;
import rw.ne.laptopdistribution.models.Banking;
import rw.ne.laptopdistribution.repositories.IBankingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BankingService {

    private final IBankingRepository bankingRepository;

    @Autowired
    public BankingService(IBankingRepository bankingRepository) {
        this.bankingRepository = bankingRepository;
    }

    public Banking createBankingTransaction(BankingDTO bankingDTO) {
        Banking banking = new Banking(bankingDTO); // Assuming Banking constructor from BankingDTO is implemented
        return bankingRepository.save(banking);
    }

    public List<Banking> getAllBankingTransactions() {
        return bankingRepository.findAll();
    }

    public Optional<Banking> getBankingTransactionById(Long id) {
        return bankingRepository.findById(id);
    }

    public void deleteBankingTransaction(Long id) {
        bankingRepository.deleteById(id);
    }
}
