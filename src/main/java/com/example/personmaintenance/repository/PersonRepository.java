package com.example.personmaintenance.repository;

import com.example.personmaintenance.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("""
            SELECT p
            FROM Person p
            WHERE lower(p.name) LIKE lower(concat('%', :keyword, '%'))
               OR lower(p.namePinyin) LIKE lower(concat('%', :keyword, '%'))
               OR lower(p.nameInitials) LIKE lower(concat('%', :keyword, '%'))
            ORDER BY p.id DESC
            """)
    List<Person> searchByNameKeyword(@Param("keyword") String keyword);
}
