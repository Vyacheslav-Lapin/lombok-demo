package ru.vlapin.demo.lombokdemo;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
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
@ComponentScan(
    includeFilters = @Filter(Aspect.class),
    excludeFilters = {
        @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)
    })
public class LombokDemoApplication {

  static void main(String[] args) {
    log.info(alphabet());
    SpringApplication.run(LombokDemoApplication.class, args);
  }

  @Contract(pure = true)
  public static String alphabet() {
    val accumulator = new StringBuilder();
    IntStream.rangeClosed('A', 'Z')
             .mapToObj(i -> (char) i)
             .forEach(accumulator::append);
    return accumulator.append("\nNow I know the alphabet!").toString();
  }

  @Bean
  @ConditionalOnBean(CatRepository.class)
  @SuppressWarnings("java:S1190")
  ApplicationRunner runner(ObjectProvider<CatRepository> catRepositoryProvider) {
    return _ -> catRepositoryProvider.ifAvailable(catRepository ->
        Stream.of("Мурзик, Барсик, Матроскин".split(", "))
              .map(Cat::new)
              .forEach(catRepository::save));
  }

  @Bean
  @ConfigurationProperties("my-properties2")
  JavaConfigBasedSetterPropertiesPlaceholderExample mySetterProperties2() {
    return new JavaConfigBasedSetterPropertiesPlaceholderExampleImpl();
  }

}
