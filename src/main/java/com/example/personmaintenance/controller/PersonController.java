package com.example.personmaintenance.controller;

import com.example.personmaintenance.entity.Gender;
import com.example.personmaintenance.entity.Person;
import com.example.personmaintenance.entity.PersonStatus;
import com.example.personmaintenance.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String list(Model model) {
        if (!model.containsAttribute("person")) {
            model.addAttribute("person", new Person());
        }
        model.addAttribute("persons", personService.findAll());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("statuses", PersonStatus.values());
        model.addAttribute("editing", false);
        return "persons";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("person") Person person,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("persons", personService.findAll());
            model.addAttribute("genders", Gender.values());
            model.addAttribute("statuses", PersonStatus.values());
            model.addAttribute("editing", false);
            return "persons";
        }
        personService.save(person);
        redirectAttributes.addFlashAttribute("success", "新增成功");
        return "redirect:/persons";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("person", personService.findById(id));
        model.addAttribute("persons", personService.findAll());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("statuses", PersonStatus.values());
        model.addAttribute("editing", true);
        return "persons";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("person") Person person,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("persons", personService.findAll());
            model.addAttribute("genders", Gender.values());
            model.addAttribute("statuses", PersonStatus.values());
            model.addAttribute("editing", true);
            return "persons";
        }
        person.setId(id);
        personService.save(person);
        redirectAttributes.addFlashAttribute("success", "修改成功");
        return "redirect:/persons";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        personService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "删除成功");
        return "redirect:/persons";
    }

    @GetMapping("/")
    public String redirectRoot() {
        return "redirect:/persons";
    }
}
