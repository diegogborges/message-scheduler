package com.luizalabs.message.scheduler.domain.entity;

import com.openpojo.reflection.impl.PojoClassFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pojo.tester.api.assertion.Method;

@SpringBootTest
class PackageModelTest extends PojoModelBase {

  @BeforeEach
  public void setUp() {
    this.pojoClasses.add(PojoClassFactory.getPojoClass(MessageScheduler.class));
    this.pojoClasses.add(PojoClassFactory.getPojoClass(MessageStatus.class));
    this.pojoClasses.add(PojoClassFactory.getPojoClass(MessageType.class));
    this.pojoClasses.add(PojoClassFactory.getPojoClass(MessageTypeScheduler.class));
  }

  @Test
  void validatePojoPayload() {
    this.builder();
    this.noArgsConstructor();
    this.allArgsConstructor();
    this.annotationEntity();
    this.annotationTable();
    this.serialVersionUid();
    this.checkNecessaryMethods(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR, Method.TO_STRING,
        Method.EQUALS, Method.HASH_CODE);
  }
}
