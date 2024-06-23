package rw.ne.laptopdistribution.dtos;

import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class SignInDTO {

    @NotBlank
    private  String email;

    @NotBlank
    private  String password;
}

