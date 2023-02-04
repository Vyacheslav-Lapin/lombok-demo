package ru.vlapin.demo.lombokdemo;

import java.util.stream.Stream;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import ru.vlapin.demo.lombokdemo.dao.CatRepository;
import ru.vlapin.demo.lombokdemo.model.Cat;
import ru.vlapin.demo.lombokdemo.model.JavaConfigBasedSetterPropertiesPlaceholderExample;
import ru.vlapin.demo.lombokdemo.model.JavaConfigBasedSetterPropertiesPlaceholderExampleImpl;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.*;

@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableHypermediaSupport(type = HAL)
@ComponentScan(includeFilters = @Filter(Aspect.class))
public class LombokDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(LombokDemoApplication.class, args);
  }

  @Bean
  ApplicationRunner runner(CatRepository catRepository) {
    return __ -> Stream.of("Мурзик, Барсик, Матроскин".split(", "))
                     .map(Cat::new)
                     .forEach(catRepository::save);
  }

  @Bean
  @ConfigurationProperties("my-properties2")
  JavaConfigBasedSetterPropertiesPlaceholderExample mySetterProperties2() {
    return new JavaConfigBasedSetterPropertiesPlaceholderExampleImpl();
  }
}
