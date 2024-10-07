package ru.vlapin.demo.lombokdemo;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
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

@Slf4j
@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableHypermediaSupport(type = HAL)
@ComponentScan(includeFilters = @Filter(Aspect.class))
public class LombokDemoApplication {

  public static void main(String[] args) {
    log.info(alphabet());
    SpringApplication.run(LombokDemoApplication.class, args);
  }

  @Contract(pure = true)
  public static @NotNull String alphabet() {
    val result = new StringBuilder();
    IntStream.rangeClosed('A', 'Z')
             .mapToObj(i -> (char) i)
             .forEach(result::append);
    return result.append("\nNow I know the alphabet!").toString();
  }

  @Bean
  @SuppressWarnings("java:S1190")
  ApplicationRunner runner(CatRepository catRepository) {
    return _ -> Stream.of("Мурзик, Барсик, Матроскин".split(", "))
                      .map(Cat::new)
                      .forEach(catRepository::save);
  }

  @Bean
  @ConfigurationProperties("my-properties2")
  JavaConfigBasedSetterPropertiesPlaceholderExample mySetterProperties2() {
    return new JavaConfigBasedSetterPropertiesPlaceholderExampleImpl();
  }

}
