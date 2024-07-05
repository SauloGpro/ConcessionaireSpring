package com.sague.springapp.controller;

import com.sague.springapp.service.model.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable("id") Integer id) {
        // Implementación para obtener una marca por ID
        Brand brand = Brand.builder().id(id).build(); // Ejemplo de respuesta
        return ResponseEntity.ok(brand);
    }

    @PostMapping
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        // Implementación para crear una marca
        return ResponseEntity.ok(brand); // Ejemplo de respuesta
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Brand> updateBrand(@PathVariable("id") Integer id, @RequestBody Brand brand) {
        // Implementación para actualizar una marca por ID
        brand.setId(id);
        return ResponseEntity.ok(brand); // Ejemplo de respuesta
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Void> deleteBrand(@PathVariable("id") Integer id) {
        // Implementación para eliminar una marca por ID
        return ResponseEntity.ok().build(); // Ejemplo de respuesta
    }
}




