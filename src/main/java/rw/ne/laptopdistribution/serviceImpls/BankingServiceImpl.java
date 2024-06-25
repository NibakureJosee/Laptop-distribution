package rw.ne.laptopdistribution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ne.laptopdistribution.dtos.BankingDTO;
import rw.ne.laptopdistribution.exceptions.BadRequestException;
import rw.ne.laptopdistribution.models.Banking;
import rw.ne.laptopdistribution.models.Customer;
import rw.ne.laptopdistribution.repositories.BankingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BankingServiceImpl implements BankingService {

    private final BankingRepository bankingRepository;
    private final EmailService emailService;
    private final CustomerService customerService;

    @Autowired
    public BankingServiceImpl(BankingRepository bankingRepository, EmailService emailService, CustomerService customerService) {
        this.bankingRepository = bankingRepository;
        this.emailService = emailService;
        this.customerService = customerService;
    }

    @Override
    public Banking createBankingTransaction(BankingDTO bankingDTO) {
        Customer customer = customerService.getCustomerById(bankingDTO.getCustomer().getId());

        if (bankingDTO.getType().equalsIgnoreCase("saving")) {
            // Saving transaction
            Banking banking = new Banking();
            banking.setCustomer(customer);
            banking.setAccount(bankingDTO.getAccount());
            banking.setAmount(bankingDTO.getAmount());
            banking.setType("saving");
            banking.setBankingDateTime(LocalDateTime.now());

            // Update customer balance
            customerService.updateCustomerBalance(customer.getId(), bankingDTO.getAmount());

            // Send email notification
            sendTransactionEmail(customer, bankingDTO.getAmount(), bankingDTO.getAccount(), "saving");

            return bankingRepository.save(banking);
        } else if (bankingDTO.getType().equalsIgnoreCase("withdraw")) {
            // Withdraw transaction
            Banking banking = new Banking();
            banking.setCustomer(customer);
            banking.setAccount(bankingDTO.getAccount());
            banking.setAmount(bankingDTO.getAmount());
            banking.setType("withdraw");
            banking.setBankingDateTime(LocalDateTime.now());

            // Check if customer has sufficient balance
            if (customer.getBalance() < bankingDTO.getAmount()) {
                throw new BadRequestException("Insufficient balance for withdrawal");
            }

            // Update customer balance
            customerService.updateCustomerBalance(customer.getId(), -bankingDTO.getAmount());

            // Send email notification
            sendTransactionEmail(customer, bankingDTO.getAmount(), bankingDTO.getAccount(), "withdraw");

            return bankingRepository.save(banking);
        } else if (bankingDTO.getType().equalsIgnoreCase("transfer")) {
            // Transfer transaction
            Customer recipient = customerService.getCustomerById(bankingDTO.getRecipientId());

            if (customer.getId().equals(recipient.getId())) {
                throw new BadRequestException("Cannot transfer to the same account");
            }

            Banking banking = new Banking();
            banking.setCustomer(customer);
            banking.setAccount(bankingDTO.getAccount());
            banking.setAmount(bankingDTO.getAmount());
            banking.setType("transfer");
            banking.setBankingDateTime(LocalDateTime.now());

            // Check if customer has sufficient balance
            if (customer.getBalance() < bankingDTO.getAmount()) {
                throw new BadRequestException("Insufficient balance for transfer");
            }

            // Update customer balances
            customerService.updateCustomerBalance(customer.getId(), -bankingDTO.getAmount());
            customerService.updateCustomerBalance(recipient.getId(), bankingDTO.getAmount());

            // Send email notifications
            sendTransactionEmail(customer, bankingDTO.getAmount(), bankingDTO.getAccount(), "transfer");
            sendTransactionEmail(recipient, bankingDTO.getAmount(), bankingDTO.getAccount(), "transfer_received");

            return bankingRepository.save(banking);
        } else {
            throw new BadRequestException("Invalid transaction type");
        }
    }

    @Override
    public List<Banking> getAllBankingTransactions() {
        return bankingRepository.findAll();
    }

    @Override
    public Optional<Banking> getBankingTransactionById(Long id) {
        return bankingRepository.findById(id);
    }

    @Override
    public void deleteBankingTransaction(Long id) {
        bankingRepository.deleteById(id);
    }

    private void sendTransactionEmail(Customer customer, double amount, String account, String transactionType) {
        String message = String.format("Dear %s %s, your %s of %.2f on your account %s has been completed successfully.",
                customer.getFirstname(), customer.getLastname(), transactionType, amount, account);
        emailService.sendEmail(customer.getEmail(), "Transaction Notification", message);
    }
}
