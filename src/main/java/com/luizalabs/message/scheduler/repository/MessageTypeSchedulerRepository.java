package com.luizalabs.message.scheduler.repository;

import com.luizalabs.message.scheduler.domain.entity.MessageTypeScheduler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTypeSchedulerRepository extends JpaRepository<MessageTypeScheduler, Long> {
}
