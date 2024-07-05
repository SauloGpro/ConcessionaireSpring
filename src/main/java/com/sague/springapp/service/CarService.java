package com.sague.springapp.service;

import com.sague.springapp.controller.dto.CarRequest;
import com.sague.springapp.controller.dto.CarResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CarService {
    CarResponse getCarById(Integer id) throws Exception;
    void deleteCarById(Integer id) throws Exception;
    CarResponse updateCarById(Integer id, CarRequest carRequest) throws Exception;
    CarResponse saveCar(CarRequest carRequest) throws Exception;
    CompletableFuture<List<CarResponse>> getAllCars() throws Exception;
}

