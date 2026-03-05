package com.example.personmaintenance.controller;

import com.example.personmaintenance.entity.Gender;
import com.example.personmaintenance.entity.Person;
import com.example.personmaintenance.entity.PersonStatus;
import com.example.personmaintenance.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> list(@RequestParam(value = "keyword", required = false) String keyword) {
        return personService.searchByNameKeyword(keyword);
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(validationErrors(bindingResult));
        }
        try {
            return ResponseEntity.ok(personService.save(person));
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", "身份证号已存在"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody Person person,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(validationErrors(bindingResult));
        }
        person.setId(id);
        try {
            return ResponseEntity.ok(personService.save(person));
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", "身份证号已存在"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/meta")
    public Map<String, Object> meta() {
        return Map.of(
                "genders", enumMeta(Gender.values()),
                "statuses", enumMeta(PersonStatus.values())
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleNotFound(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    private Map<String, String> validationErrors(BindingResult bindingResult) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    private Map<String, String>[] enumMeta(Enum<?>[] values) {
        @SuppressWarnings("unchecked")
        Map<String, String>[] result = new Map[values.length];
        for (int i = 0; i < values.length; i++) {
            Enum<?> v = values[i];
            String label;
            if (v instanceof Gender g) {
                label = g.getLabel();
            } else if (v instanceof PersonStatus s) {
                label = s.getLabel();
            } else {
                label = v.name();
            }
            result[i] = Map.of("value", v.name(), "label", label);
        }
        return result;
    }
}
