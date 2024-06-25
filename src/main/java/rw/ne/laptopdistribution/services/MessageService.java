package rw.ne.laptopdistribution.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ne.laptopdistribution.dtos.MessageDTO;
import rw.ne.laptopdistribution.models.Customer;
import rw.ne.laptopdistribution.models.Message;
import rw.ne.laptopdistribution.repositories.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(MessageDTO messageDTO) {
        Message message = new Message();

        Customer customer = new Customer();
        customer.setId(messageDTO.getCustomer().getId()); // Assuming customer ID is provided in DTO

        message.setCustomer(customer);
        message.setMessage(messageDTO.getMessage());
        message.setDateTime(LocalDateTime.now());

        return messageRepository.save(message);
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
