package com.onehoeyman.battle.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class EquipmentController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/equipment/{id}")
    public String index(@PathVariable int id) {

        return "Greetings from Spring Boot!";
    }
    @RequestMapping(value = "/equipment", method = RequestMethod.GET)
    public ModelAndView showForm() {

        return null;
    }
}
