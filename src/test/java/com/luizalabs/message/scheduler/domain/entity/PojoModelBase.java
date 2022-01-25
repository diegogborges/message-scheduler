package com.luizalabs.message.scheduler.domain.entity;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.SerializableMustHaveSerialVersionUIDRule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.jupiter.api.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class PojoModelBase {

  protected List<PojoClass> pojoClasses = new ArrayList<PojoClass>();

  protected void annotationEntity() {
    Assertions.assertDoesNotThrow(() ->
        pojoClasses.forEach(clazz -> Stream.of(clazz.getClazz().getAnnotations())
            .filter(annotation -> annotation instanceof Entity)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("class does not entity annotation"))
        ));
  }

  protected void annotationTable() {
    Assertions.assertDoesNotThrow(() ->
        pojoClasses.forEach(clazz -> Stream.of(clazz.getClazz().getAnnotations())
            .filter(annotation -> annotation instanceof Table)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("class does not table annotation"))
        ));
  }

  protected void noArgsConstructor() {
    Assertions.assertDoesNotThrow(() ->
        pojoClasses.forEach(clazz -> Stream.of(clazz.getClazz().getConstructors())
            .filter(constructor -> constructor.getParameterTypes().length == 0)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("class does not NoArgsContructor"))
        ));
  }

  protected void allArgsConstructor() {
    Assertions.assertDoesNotThrow(() ->
        pojoClasses.forEach(clazz -> Stream.of(clazz.getClazz().getConstructors())
            .filter(constructor -> constructor.getParameterTypes().length > 0)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("class does not AllArgsContructor"))
        ));
  }

  protected void builder() {
    Assertions.assertDoesNotThrow(() ->
        pojoClasses.forEach(clazz ->
            Stream.of(clazz.getClazz().getDeclaredClasses())
                .filter(c -> c.getName().contains("Builder"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("class does not builder"))
        ));
  }

  protected void serialVersionUid() {
    pojoClasses.forEach(clazz -> ValidatorBuilder.create()
        .with(new SerializableMustHaveSerialVersionUIDRule())
        .build()
        .validate(clazz));
  }

  protected void checkNecessaryMethods(final Method... methods) {
    pojoClasses.forEach(clazz ->
        assertPojoMethodsFor(clazz.getClazz())
            .testing(methods)
            .areWellImplemented());
  }
}
