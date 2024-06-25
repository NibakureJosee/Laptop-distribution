package rw.ne.laptopdistribution.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class BankingDTO {

    private Long id;

    @NotNull(message = "Customer is required")
    private CustomerDTO customer;

    @NotBlank(message = "Account is required")
    private String account;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Type is required")
    private String type; // saving, withdraw, transfer

    private LocalDateTime bankingDateTime;
}
