package pl.jagiela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.jagiela.dto.CategoryDto;
import pl.jagiela.model.Category;
import pl.jagiela.service.CategoryService;

import java.util.List;


//Kontroler zawierający wszystkie endpointy dotyczące klasy Category obsługujące operacje na bazie danych
@CrossOrigin
@RestController
@RequestMapping("/api/category")//główna ścieżka do endpointów
public class CategoryController {

    private CategoryService categoryService;


    //konstruktor wstrzykujący instancję typu CategoryService do wykonywania działań na bazie danych.
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //endpoint pobierający i zwracający listę wszystkich kategorii przekształconych na obiekt CategoryDto w celu pobrania tylko wybranych pól.
    @GetMapping("")
    public List<CategoryDto> getCategories(){
        return categoryService.getCategories();
    }

    //endpoint dodający nową kategorię do bazy danych
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
    }

    //endpoint który usuwa kategorię po id z bazy danych
    @DeleteMapping("/{id}")
    public void deleteCategory (@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

    //Znajdywanie kategorii po id nazwie
    @GetMapping("/find")
    public CategoryDto findByName(@RequestParam String name){
        return categoryService.findCategory(name);
    }

    //Edytowanie podanej kategorii.
    @PutMapping("")
    public void editCategory(@RequestBody Category category){
        categoryService.editCategory(category);
    }

}
