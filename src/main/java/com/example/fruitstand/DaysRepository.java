package com.example.fruitstand;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DaysRepository extends CrudRepository<Days, Long> {
    List<Days> findById(long id);
}
