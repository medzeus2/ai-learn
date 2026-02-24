package com.example.personmaintenance.service;

import com.example.personmaintenance.entity.Person;
import com.example.personmaintenance.repository.PersonRepository;
import com.example.personmaintenance.util.NamePinyinUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public List<Person> searchByNameKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return personRepository.findAll();
        }
        return personRepository.searchByNameKeyword(keyword.trim().toLowerCase());
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("人员不存在，id=" + id));
    }

    public Person save(Person person) {
        fillNameIndexFields(person);
        return personRepository.save(person);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    private void fillNameIndexFields(Person person) {
        person.setNamePinyin(NamePinyinUtils.toPinyin(person.getName()));
        person.setNameInitials(NamePinyinUtils.toInitials(person.getName()));
    }
}
