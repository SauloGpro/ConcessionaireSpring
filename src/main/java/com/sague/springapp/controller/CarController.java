package com.sague.springapp.controller;

import com.sague.springapp.service.model.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Integer id) {
        // Implementación para obtener un carro por ID
        Car car = Car.builder().id(id).build(); // Ejemplo de respuesta
        return ResponseEntity.ok(car);
    }

    @PostMapping
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        // Implementación para crear un carro
        return ResponseEntity.ok(car); // Ejemplo de respuesta
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Car> updateCar(@PathVariable("id") Integer id, @RequestBody Car car) {
        // Implementación para actualizar un carro por ID
        car.setId(id);
        return ResponseEntity.ok(car); // Ejemplo de respuesta
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Integer id) {
        // Implementación para eliminar un carro por ID
        return ResponseEntity.ok().build(); // Ejemplo de respuesta
    }
}




