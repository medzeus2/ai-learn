package com.example.personmaintenance.repository;

import com.example.personmaintenance.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
