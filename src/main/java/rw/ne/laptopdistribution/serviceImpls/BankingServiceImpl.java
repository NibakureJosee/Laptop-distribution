package rw.ne.laptopdistribution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ne.laptopdistribution.dtos.BankingDTO;
import rw.ne.laptopdistribution.exceptions.BadRequestException;
import rw.ne.laptopdistribution.models.Banking;
import rw.ne.laptopdistribution.models.Customer;
import rw.ne.laptopdistribution.repositories.BankingRepository;
import rw.ne.laptopdistribution.repositories.CustomerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BankingServiceImpl implements BankingService {

    private final BankingRepository bankingRepository;
    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    @Autowired
    public BankingServiceImpl(BankingRepository bankingRepository, CustomerRepository customerRepository, EmailService emailService) {
        this.bankingRepository = bankingRepository;
        this.customerRepository = customerRepository;
        this.emailService = emailService;
    }

    @Override
    public Banking createBankingTransaction(BankingDTO bankingDTO) {
        validateBankingDTO(bankingDTO);

        Customer customer = customerRepository.findById(bankingDTO.getCustomer().getId())
                .orElseThrow(() -> new BadRequestException("Customer not found"));

        Banking banking = new Banking();
        banking.setCustomer(customer);
        banking.setAccount(bankingDTO.getAccount());
        banking.setAmount(bankingDTO.getAmount());
        banking.setType(bankingDTO.getType());
        banking.setBankingDateTime(LocalDateTime.now());

        if ("saving".equalsIgnoreCase(bankingDTO.getType())) {
            customer.setBalance(customer.getBalance() + bankingDTO.getAmount());
            sendEmailNotification(customer, "saving", bankingDTO.getAmount(), bankingDTO.getAccount());
        } else if ("withdraw".equalsIgnoreCase(bankingDTO.getType())) {
            if (customer.getBalance() < bankingDTO.getAmount()) {
                throw new BadRequestException("Insufficient balance for withdrawal");
            }
            customer.setBalance(customer.getBalance() - bankingDTO.getAmount());
            sendEmailNotification(customer, "withdraw", bankingDTO.getAmount(), bankingDTO.getAccount());
        } else if ("transfer".equalsIgnoreCase(bankingDTO.getType())) {
            Customer recipient = customerRepository.findByAccount(bankingDTO.getRecipientAccount())
                    .orElseThrow(() -> new BadRequestException("Recipient not found"));

            customer.setBalance(customer.getBalance() - bankingDTO.getAmount());
            recipient.setBalance(recipient.getBalance() + bankingDTO.getAmount());

            sendEmailNotification(customer, "transfer", bankingDTO.getAmount(), bankingDTO.getAccount());
            sendEmailNotification(recipient, "received transfer", bankingDTO.getAmount(), bankingDTO.getRecipientAccount());
        }

        customerRepository.save(customer);
        return bankingRepository.save(banking);
    }

    private void validateBankingDTO(BankingDTO bankingDTO) {
        if (bankingDTO.getAmount() <= 0) {
            throw new BadRequestException("Amount must be greater than zero");
        }
        // Add more validations as needed
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

    private void sendEmailNotification(Customer customer, String transactionType, Double amount, String account) {
        String emailSubject = "Transaction Notification";
        String emailBody = String.format("Dear %s %s,\nYour %s of %.2f on your account %s has been completed successfully.",
                customer.getFirstname(), customer.getLastname(), transactionType, amount, account);

        emailService.sendEmail(customer.getEmail(), emailSubject, emailBody);
    }
}
