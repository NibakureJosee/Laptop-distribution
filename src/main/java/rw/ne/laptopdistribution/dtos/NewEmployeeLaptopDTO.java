package rw.ne.laptopdistribution.dtos;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class NewEmployeeLaptopDTO {

    @NotNull
    public UUID employeeId;

    @NotNull
    public UUID laptopId;
}
