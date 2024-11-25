package ru.vlapin.demo.lombokdemo.model;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants
public record Customer(Long id, String name) {}
