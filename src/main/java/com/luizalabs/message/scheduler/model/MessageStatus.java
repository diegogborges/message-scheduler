package com.luizalabs.message.scheduler.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "message_status",
    uniqueConstraints = {
      @UniqueConstraint(name = "UK_MESSAGE_STATUS_DESCRIPTION", columnNames = "description")
    }
)
public class MessageStatus implements Serializable {

  private static final long serialVersionUID = 5167528624176922372L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(length = 100)
  @NotNull
  private String description;
}
