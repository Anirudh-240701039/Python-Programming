package com.example.cricket.controller;

import com.example.cricket.model.Cricketer;
import com.example.cricket.model.Role;
import com.example.cricket.repository.CricketerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cricketers")
public class CricketerController {

    private final CricketerRepository cricketerRepository;

    public CricketerController(CricketerRepository cricketerRepository) {
        this.cricketerRepository = cricketerRepository;
    }

    @GetMapping
    public String list(@RequestParam(value = "q", required = false) String query, Model model) {
        if (query != null && !query.isBlank()) {
            model.addAttribute("cricketers", cricketerRepository.findByNameContainingIgnoreCase(query));
            model.addAttribute("q", query);
        } else {
            model.addAttribute("cricketers", cricketerRepository.findAll());
        }
        return "cricketers/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("cricketer", new Cricketer());
        model.addAttribute("roles", Role.values());
        return "cricketers/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Cricketer cricketer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "cricketers/form";
        }
        cricketerRepository.save(cricketer);
        return "redirect:/cricketers";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Cricketer cricketer = cricketerRepository.findById(id).orElseThrow();
        model.addAttribute("cricketer", cricketer);
        model.addAttribute("roles", Role.values());
        return "cricketers/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Cricketer cricketer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "cricketers/form";
        }
        cricketer.setId(id);
        cricketerRepository.save(cricketer);
        return "redirect:/cricketers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        cricketerRepository.deleteById(id);
        return "redirect:/cricketers";
    }
}
