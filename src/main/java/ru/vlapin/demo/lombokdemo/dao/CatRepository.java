package ru.vlapin.demo.lombokdemo.dao;

import java.util.UUID;

import ru.vlapin.demo.lombokdemo.model.Cat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, UUID> {
}
