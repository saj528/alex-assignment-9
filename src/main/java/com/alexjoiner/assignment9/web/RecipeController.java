package com.alexjoiner.assignment9.web;

import com.alexjoiner.assignment9.domain.Recipe;
import com.alexjoiner.assignment9.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecipeController {

    @Autowired
    private FileService fileService;

    @GetMapping("/all-recipes")
    public List<Recipe> getAllRecipes () {
        return fileService.getAllRecipes();
    }

    @GetMapping("/vegan")
    public List<Recipe> getVeganRecipes(){
        return fileService
                .getAllRecipes()
                .stream()
                .filter(Recipe::getVegan)
                .collect(Collectors.toList());
    }

    @GetMapping("/gluten-free")
    public List<Recipe> getGlutenFreeRecipes(){
        return fileService
                .getAllRecipes()
                .stream()
                .filter(Recipe::getGlutenFree)
                .collect(Collectors.toList());
    }

    @GetMapping("/vegan-and-gluten-free")
    public List<Recipe> getGlutenFreeAndVeganRecipes(){
        return fileService
                .getAllRecipes()
                .stream()
                .filter(Recipe::getGlutenFree)
                .filter(Recipe::getVegan)
                .collect(Collectors.toList());
    }

    @GetMapping("/vegetarian")
    public List<Recipe> getVegetarianRecipes(){
        return fileService
                .getAllRecipes()
                .stream()
                .filter(Recipe::getVegetarian)
                .collect(Collectors.toList());
    }
}
