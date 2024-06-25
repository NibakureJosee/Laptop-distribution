package rw.ne.laptopdistribution.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class MessageDTO {

    private Long id;

    @NotNull(message = "Customer is required")
    private CustomerDTO customer;

    @NotBlank(message = "Message is required")
    private String message;

    private LocalDateTime dateTime;
}
