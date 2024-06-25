package rw.ne.laptopdistribution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ne.laptopdistribution.dtos.BankingDTO;
import rw.ne.laptopdistribution.models.Banking;
import rw.ne.laptopdistribution.services.BankingService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bankings")
public class BankingController {

    private final BankingService bankingService;

    @Autowired
    public BankingController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @PostMapping
    public ResponseEntity<Banking> createBankingTransaction(@RequestBody BankingDTO bankingDTO) {
        Banking createdBanking = bankingService.createBankingTransaction(bankingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBanking);
    }

    @GetMapping
    public ResponseEntity<List<Banking>> getAllBankingTransactions() {
        List<Banking> bankings = bankingService.getAllBankingTransactions();
        return ResponseEntity.ok(bankings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banking> getBankingTransactionById(@PathVariable Long id) {
        Optional<Banking> bankingOptional = bankingService.getBankingTransactionById(id);
        return bankingOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankingTransaction(@PathVariable Long id) {
        bankingService.deleteBankingTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
