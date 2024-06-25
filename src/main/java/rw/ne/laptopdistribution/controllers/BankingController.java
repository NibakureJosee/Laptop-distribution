package rw.ne.laptopdistribution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rw.ne.laptopdistribution.dtos.BankingDTO;
import rw.ne.laptopdistribution.models.Banking;
import rw.ne.laptopdistribution.services.BankingService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banking")
public class BankingController {

    private final BankingService bankingService;

    @Autowired
    public BankingController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<Banking> createBankingTransaction(@Validated @RequestBody BankingDTO bankingDTO) {
        Banking banking = bankingService.createBankingTransaction(bankingDTO);
        return new ResponseEntity<>(banking, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Banking>> getAllBankingTransactions() {
        List<Banking> bankingTransactions = bankingService.getAllBankingTransactions();
        return ResponseEntity.ok(bankingTransactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banking> getBankingTransactionById(@PathVariable Long id) {
        Optional<Banking> bankingTransaction = bankingService.getBankingTransactionById(id);
        return bankingTransaction.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankingTransaction(@PathVariable Long id) {
        bankingService.deleteBankingTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
