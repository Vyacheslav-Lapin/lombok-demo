package ru.vlapin.demo.lombokdemo.stable.constructors;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor(force = true, onConstructor = @__(@Autowired))
public class NoArgsConstructorDemo {
}
