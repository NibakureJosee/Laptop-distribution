package rw.ne.laptopdistribution.services;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

}
