package com.alexjoiner.assignment9.service;

import com.alexjoiner.assignment9.domain.Recipe;
import com.alexjoiner.assignment9.repository.RecipeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private RecipeRepository recipeRepository;

    private List<Recipe> parseRecipes(){

        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',')
                .withEscape('\\')
                .withHeader("Cooking Minutes", "Dairy Free", "Gluten Free", "Instructions", "Preparation Minutes", "Price Per Serving", "Ready In Minutes", "Servings", "Spoonacular Score", "Title", "Vegan", "Vegetarian")
                .withSkipHeaderRecord()
                .withIgnoreSurroundingSpaces();

        try (
                Reader in = new FileReader("recipes.txt"))
        {
            Iterable<CSVRecord> records = csvFormat.parse(in);
            for (CSVRecord record : records) {
                Integer cookingMinutes = Integer.parseInt(record.get("Cooking Minutes"));
                Boolean dairyFree = Boolean.parseBoolean(record.get("Dairy Free"));
                Boolean glutenFree = Boolean.parseBoolean(record.get("Gluten Free"));
                String instructions = record.get("Instructions");
                Double prepMin = Double.parseDouble(record.get("Preparation Minutes"));
                Double price = Double.parseDouble(record.get("Price Per Serving"));
                Integer readyInMin = Integer.parseInt(record.get("Ready In Minutes"));
                Integer servings = Integer.parseInt(record.get("Servings"));
                Double score = Double.parseDouble(record.get("Spoonacular Score"));
                String title = record.get("Title");
                Boolean vegan = Boolean.parseBoolean(record.get("Vegan"));
                Boolean vegetarian = Boolean.parseBoolean(record.get("Vegetarian"));

                Recipe recipe = new Recipe();
                recipe.setCookingMinutes(cookingMinutes);
                recipe.setDairyFree(dairyFree);
                recipe.setGlutenFree(glutenFree);
                recipe.setInstructions(instructions);
                recipe.setPreparationMinutes(prepMin);
                recipe.setPricePerServing(price);
                recipe.setReadyInMinutes(readyInMin);
                recipe.setServings(servings);
                recipe.setSpoonacularScore(score);
                recipe.setTitle(title);
                recipe.setVegan(vegan);
                recipe.setVegetarian(vegetarian);

                recipeRepository.getRecipes().add(recipe);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return recipeRepository.getRecipes();
    }

    public List<Recipe> getAllRecipes () {
        if (recipeRepository.getRecipes().size() == 0) {
            return parseRecipes();
        }
        return recipeRepository.getRecipes();
    }

}
