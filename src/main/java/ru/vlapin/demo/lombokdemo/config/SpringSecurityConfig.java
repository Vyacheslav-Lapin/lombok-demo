package ru.vlapin.demo.lombokdemo.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.Customizer.*;

/**
 * SpringSecurityConfig.
 *
 * @see <a href="https://youtu.be/TytSz7u1xQ8">"Test-Driven Security" by Eleftheria Stain-Kousathana, SpringOne, 2021</a>
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

  @Bean
  @SneakyThrows
  public SecurityFilterChain securityFilterChain(HttpSecurity http) {
    return http.httpBasic(withDefaults())
//        .csrf(AbstractHttpConfigurer::disable) //don't do this!
        .authorizeHttpRequests(it -> it
            .requestMatchers(GET,"/about").permitAll()
            .requestMatchers(POST,"/about").hasRole("ADMIN")
            .requestMatchers("/testExceptionHandler").permitAll()
            .requestMatchers("/makeNPE").permitAll()
            .requestMatchers("/api/*").permitAll()
            .requestMatchers("/submissions").hasRole("SPEAKER")
            .anyRequest().authenticated())
        .build();
  }
}
