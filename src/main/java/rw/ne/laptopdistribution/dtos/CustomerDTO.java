package rw.ne.laptopdistribution.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class CustomerDTO {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    @Pattern(regexp = "[0-9]{9,10}", message = "Mobile number is not valid")
    private String mobile;

    @Email
    private String email;

    @NotBlank
    private String account;

    private LocalDate dob;

    private double balance;
}
