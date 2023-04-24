package ru.vlapin.demo.lombokdemo.model;

public sealed interface JavaConfigBasedSetterPropertiesPlaceholderExample
        permits JavaConfigBasedSetterPropertiesPlaceholderExampleImpl {

  String getHost();

  Integer getPort();
}
