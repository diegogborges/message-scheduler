package com.luizalabs.message.scheduler.repository;

import com.luizalabs.message.scheduler.model.MessageScheduler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageSchedulerRepository extends JpaRepository<MessageScheduler, Long> {
}
