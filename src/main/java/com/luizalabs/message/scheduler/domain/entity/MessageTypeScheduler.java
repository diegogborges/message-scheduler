package com.luizalabs.message.scheduler.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name = "message_type_scheduler")
public class MessageTypeScheduler implements Serializable {

  private static final long serialVersionUID = 4542766819946830073L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "message_scheduler_id", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MESSAGE_SCHEDULER_MESSAGE_SCHEDULER")
  )
  private MessageScheduler messageScheduler;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "message_status_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "FK_MESSAGE_SCHEDULER_MESSAGE_STATUS")
  )
  private MessageStatus messageStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "message_type_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "FK_MESSAGE_SCHEDULER_MESSAGE_TYPE")
  )
  private MessageType messageType;

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
