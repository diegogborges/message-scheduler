package com.luizalabs.message.scheduler.domain.entity;

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
import org.hibernate.annotations.Type;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "message_type",
    uniqueConstraints = {
        @UniqueConstraint(name = "UK_MESSAGE_TYPE_DESCRIPTION", columnNames = "description")
    }
)
public class MessageType implements Serializable {

  private static final long serialVersionUID = 4901913277041755528L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(length = 100)
  @NotNull
  private String description;

  @NotNull
  @Type(type = "numeric_boolean")
  private Boolean status;
}
