package ru.vlapin.demo.lombokdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlapin.demo.lombokdemo.model.Cat;

import java.util.UUID;

//@RepositoryRestResource
public interface CatRepository extends JpaRepository<Cat, UUID> {
}
