package rw.ne.laptopdistribution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ne.laptopdistribution.dtos.BankingDTO;
import rw.ne.laptopdistribution.dtos.CustomerDTO;
import rw.ne.laptopdistribution.dtos.MessageDTO;
import rw.ne.laptopdistribution.models.Banking;
import rw.ne.laptopdistribution.models.Customer;
import rw.ne.laptopdistribution.models.Message;
import rw.ne.laptopdistribution.repositories.IMessageRepository;
import rw.ne.laptopdistribution.repositories.ICustomerRepository;
import rw.ne.laptopdistribution.services.EmailService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final IMessageRepository messageRepository;
    private final ICustomerRepository customerRepository;
    private final EmailService emailService;

    @Autowired
    public MessageService(IMessageRepository messageRepository, ICustomerRepository customerRepository, EmailService emailService) {
        this.messageRepository = messageRepository;
        this.customerRepository = customerRepository;
        this.emailService = emailService;
    }

    public Message createMessage(MessageDTO messageDTO) {
        Message message = new Message();

        Customer customer = customerRepository.findById(messageDTO.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found")); // Handle this exception better

        message.setCustomer(customer);
        message.setMessage(messageDTO.getMessage());
        message.setDateTime(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public void sendTransactionNotification(Customer customer, BankingDTO bankingDTO) {
        String transactionType = bankingDTO.getType();
        String transactionDescription = getTransactionDescription(transactionType);
        String emailSubject = String.format("Transaction Notification: %s Completed", transactionDescription);
        String emailBody = String.format("Dear %s %s,\n\nYour %s of %.2f on your account %s has been completed successfully.",
                customer.getFirstName(), customer.getLastName(), transactionDescription, bankingDTO.getAmount(), bankingDTO.getAccount());

        emailService.sendEmail(customer.getEmail(), emailSubject, emailBody);
    }

    private String getTransactionDescription(String transactionType) {
        switch (transactionType) {
            case "saving":
                return "Saving";
            case "withdraw":
                return "Withdrawal";
            case "transfer":
                return "Transfer";
            default:
                return "Transaction";
        }
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
