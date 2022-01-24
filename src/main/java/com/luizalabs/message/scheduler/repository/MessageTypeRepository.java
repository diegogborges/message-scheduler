package com.luizalabs.message.scheduler.repository;

import com.luizalabs.message.scheduler.domain.entity.MessageType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTypeRepository extends JpaRepository<MessageType, Long> {
}
