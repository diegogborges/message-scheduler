package com.luizalabs.message.scheduler.repository;

import com.luizalabs.message.scheduler.model.MessageTypeScheduler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTypeSchedulerRepository extends JpaRepository<MessageTypeScheduler, Long> {
}
