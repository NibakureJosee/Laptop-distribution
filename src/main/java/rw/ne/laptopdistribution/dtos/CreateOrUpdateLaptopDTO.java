package rw.ne.laptopdistribution.dtos;

import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class CreateOrUpdateLaptopDTO {

    @NotBlank
    private String manufacturer;

    @NotBlank
    private String model;

    @NotBlank
    private String serialNumber;
}
