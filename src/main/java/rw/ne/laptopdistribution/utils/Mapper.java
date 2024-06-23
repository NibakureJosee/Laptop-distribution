package rw.ne.laptopdistribution.utils;

import org.modelmapper.ModelMapper;
import rw.ne.laptopdistribution.models.User;

public class Mapper {

    public static ModelMapper modelMapper = new ModelMapper();

    public static User getUserFromDTO(Object object) {
        return modelMapper.map(object, User.class);
    }


}
