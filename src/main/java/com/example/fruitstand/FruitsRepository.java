package com.example.fruitstand;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FruitsRepository extends CrudRepository<Fruits, Long> {
 List<Fruits> findByDay_Id(long id);
}
