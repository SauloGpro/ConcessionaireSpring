package com.sague.springapp.service.impl;

import com.sague.springapp.controller.dto.CarRequest;
import com.sague.springapp.controller.dto.CarResponse;
import com.sague.springapp.repository.CarRepository;
import com.sague.springapp.repository.entity.BrandEntity;
import com.sague.springapp.repository.entity.CarEntity;
import com.sague.springapp.service.CarService;
import com.sague.springapp.service.model.Brand;
import com.sague.springapp.service.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    @Async("taskExecutor")
    public CarResponse getCarById(Integer id) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Starting search for car with ID: " + id);

        Optional<CarEntity> carEntity = carRepository.findById(id);

        if (carEntity.isPresent()) {
            long endTime = System.currentTimeMillis();
            log.info("Total time to find car with ID " + id + ": " + (endTime - startTime) + " ms");

            return mapToCarResponse(mapToCar(carEntity.get()));
        } else {
            long endTime = System.currentTimeMillis();
            log.warn("Car with ID " + id + " not found. Search time: " + (endTime - startTime) + " ms");

            throw new Exception("Car not found");
        }
    }

    @Override
    @Async("taskExecutor")
    public void deleteCarById(Integer id) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Starting deletion for car with ID: " + id);

        Optional<CarEntity> carEntity = carRepository.findById(id);
        if (carEntity.isPresent()) {
            carRepository.deleteById(id);
            long endTime = System.currentTimeMillis();
            log.info("Car with ID " + id + " deleted successfully. Total time: " + (endTime - startTime) + " ms");
        } else {
            long endTime = System.currentTimeMillis();
            log.warn("Car with ID " + id + " not found. Deletion time: " + (endTime - startTime) + " ms");
            throw new Exception("Car not found");
        }
    }

    @Override
    @Async("taskExecutor")
    public CarResponse updateCarById(Integer id, CarRequest carRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Starting update for car with ID: " + id);

        Optional<CarEntity> carEntity = carRepository.findById(id);
        if (carEntity.isPresent()) {
            Car updatedCar = mapToCar(carRequest);
            updatedCar.setId(id);
            carRepository.save(mapToCarEntity(updatedCar));

            long endTime = System.currentTimeMillis();
            log.info("Car with ID " + id + " updated successfully. Total time: " + (endTime - startTime) + " ms");

            return mapToCarResponse(updatedCar);
        } else {
            long endTime = System.currentTimeMillis();
            log.warn("Car with ID " + id + " not found. Update time: " + (endTime - startTime) + " ms");
            throw new Exception("Car not found");
        }
    }

    @Override
    @Async("taskExecutor")
    public CarResponse saveCar(CarRequest carRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Starting save for new car");

        Car car = mapToCar(carRequest);
        CarEntity savedCarEntity = carRepository.save(mapToCarEntity(car));

        long endTime = System.currentTimeMillis();
        log.info("New car saved successfully. Total time: " + (endTime - startTime) + " ms");

        return mapToCarResponse(mapToCar(savedCarEntity));
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<List<CarResponse>> getAllCars() throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Starting to fetch all cars");

        List<CarEntity> carEntities = carRepository.findAll();
        List<CarResponse> carResponses = carEntities.stream()
                .map(carEntity -> mapToCarResponse(mapToCar(carEntity)))
                .collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        log.info("Total time to fetch all cars: " + (endTime - startTime) + " ms");

        return CompletableFuture.completedFuture(carResponses);
    }

    private Car mapToCar(CarRequest carRequest) {
        return Car.builder()
                .brand(Brand.builder().id(carRequest.getBrandId()).build())
                .model(carRequest.getModel())
                .mileage(carRequest.getMileage())
                .price(carRequest.getPrice())
                .year(carRequest.getYear())
                .description(carRequest.getDescription())
                .colour(carRequest.getColour())
                .fuelType(carRequest.getFuelType())
                .numDoors(carRequest.getNumDoors())
                .build();
    }

    private Car mapToCar(CarEntity carEntity) {
        return Car.builder()
                .id(carEntity.getId())
                .brand(mapToBrand(carEntity.getBrand()))
                .model(carEntity.getModel())
                .mileage(carEntity.getMileage())
                .price(carEntity.getPrice())
                .year(carEntity.getYear())
                .description(carEntity.getDescription())
                .colour(carEntity.getColour())
                .fuelType(carEntity.getFuelType())
                .numDoors(carEntity.getNumDoors())
                .build();
    }

    private CarEntity mapToCarEntity(Car car) {
        return CarEntity.builder()
                .id(car.getId())
                .brand(BrandEntity.builder().id(car.getBrand().getId()).build())
                .model(car.getModel())
                .mileage(car.getMileage())
                .price(car.getPrice())
                .year(car.getYear())
                .description(car.getDescription())
                .colour(car.getColour())
                .fuelType(car.getFuelType())
                .numDoors(car.getNumDoors())
                .build();
    }

    private CarResponse mapToCarResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand().getName())
                .model(car.getModel())
                .mileage(car.getMileage())
                .price(car.getPrice())
                .year(car.getYear())
                .description(car.getDescription())
                .colour(car.getColour())
                .fuelType(car.getFuelType())
                .numDoors(car.getNumDoors())
                .build();
    }

    private Brand mapToBrand(BrandEntity brandEntity) {
        return Brand.builder()
                .id(brandEntity.getId())
                .name(brandEntity.getName())
                .warranty(brandEntity.getWarranty())
                .country(brandEntity.getCountry())
                .build();
    }
}









