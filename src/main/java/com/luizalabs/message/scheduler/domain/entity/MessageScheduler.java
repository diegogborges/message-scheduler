package com.luizalabs.message.scheduler.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "message_scheduler")
public class MessageScheduler implements Serializable {

  private static final long serialVersionUID = 3444162423391093483L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "send_date")
  @NotNull
  private LocalDateTime sendDate;

  @Column(length = 100)
  private String email;

  @Column(length = 100)
  private String phone;

  @Column(name = "customer_uuid", length = 100)
  private String customerUuid;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  private void beforePersist() {
    this.createdAt = LocalDateTime.now();
    beforeUpdate();
  }

  @PreUpdate
  private void beforeUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}