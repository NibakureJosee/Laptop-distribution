package rw.ne.laptopdistribution.services;


import rw.ne.laptopdistribution.models.User;

public interface IUserService {

    User create(User user);

    boolean isNotUnique(User user);

    void validateNewRegistration(User user);

    User getLoggedInUser();

}