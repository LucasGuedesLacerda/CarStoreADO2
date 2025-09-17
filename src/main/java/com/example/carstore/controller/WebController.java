package com.example.carstore.controller;

import com.example.carstore.model.Car;
import com.example.carstore.service.GenericService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    @Autowired
    private GenericService<Car> carService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("car", new Car());
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("totalCars", carService.findAll().size());
        return "dashboard";
    }

    @PostMapping("/cars")
    public String saveCar(@Valid @ModelAttribute Car car,
                          BindingResult bindingResult,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cars", carService.findAll());
            return "index";
        }

        try {
            carService.save(car);
            redirectAttributes.addFlashAttribute("successMessage", "Carro salvo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao salvar carro: " + e.getMessage());
        }

        return "redirect:/";
    }

    @GetMapping("/cars/{id}/edit")
    public String editCarForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        carService.findById(id).ifPresentOrElse(
                car -> {
                    model.addAttribute("car", car);
                    model.addAttribute("cars", carService.findAll());
                },
                () -> redirectAttributes.addFlashAttribute("errorMessage", "Carro não encontrado!")
        );
        return carService.findById(id).isPresent() ? "index" : "redirect:/";
    }

    @PostMapping("/cars/{id}")
    public String updateCar(@PathVariable Long id,
                            @Valid @ModelAttribute Car car,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            car.setId(id);
            model.addAttribute("cars", carService.findAll());
            return "index";
        }

        try {
            car.setId(id);
            carService.update(car);
            redirectAttributes.addFlashAttribute("successMessage", "Carro atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar carro: " + e.getMessage());
        }

        return "redirect:/";
    }

    @PostMapping("/cars/{id}/delete")
    public String deleteCar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            if (carService.deleteById(id)) {
                redirectAttributes.addFlashAttribute("successMessage", "Carro deletado com sucesso!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Carro não encontrado!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao deletar carro: " + e.getMessage());
        }

        return "redirect:/";
    }
}