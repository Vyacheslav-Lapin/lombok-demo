package ru.vlapin.demo.lombokdemo.dao;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlapin.demo.lombokdemo.model.Cat;

public interface CatRepository extends JpaRepository<Cat, UUID> {
}
