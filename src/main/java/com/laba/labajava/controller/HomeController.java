package com.laba.labajava.controller;

import com.laba.labajava.model.State;
import com.laba.labajava.service.RiaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {

    private final RiaService riaService;

    public HomeController(RiaService riaService) {
        this.riaService = riaService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<State> states = riaService.getStates();
        model.addAttribute("states", states);
        return "index"; // шаблон index.html має бути у папці src/main/resources/templates
    }
}
