package com.ne_2024.Java.Repositories;

import com.ne_2024.Java.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessagRepository extends JpaRepository<Message, Long> {
}
