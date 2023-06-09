package com.springboot.blog.controller;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(
        name = "CRUD REST API for Category"
)
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Build add category REST api
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategoryDto = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategoryDto, HttpStatus.CREATED);
    }

    // get category REST api
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(name ="id") Long categoryId){
        CategoryDto categoryDto = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // update category rest api
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable("id") Long categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
    }

    // delete category rest api
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
