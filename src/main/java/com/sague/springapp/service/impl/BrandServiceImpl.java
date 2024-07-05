package com.sague.springapp.service.impl;

import com.sague.springapp.controller.dto.BrandRequest;
import com.sague.springapp.controller.dto.BrandResponse;
import com.sague.springapp.repository.BrandRepository;
import com.sague.springapp.repository.entity.BrandEntity;
import com.sague.springapp.service.BrandService;
import com.sague.springapp.service.model.Brand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    @Async("taskExecutor")
    public BrandResponse getBrandById(Integer id) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Starting search for brand with ID: " + id);

        Optional<BrandEntity> brandEntity = brandRepository.findById(id);
        if (brandEntity.isPresent()) {
            long endTime = System.currentTimeMillis();
            log.info("Total time to find brand with ID " + id + ": " + (endTime - startTime) + " ms");

            return mapToBrandResponse(mapToBrand(brandEntity.get()));
        } else {
            long endTime = System.currentTimeMillis();
            log.warn("Brand with ID " + id + " not found. Search time: " + (endTime - startTime) + " ms");

            throw new Exception("Brand not found");
        }
    }

    @Override
    @Async("taskExecutor")
    public void deleteBrandById(Integer id) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Starting deletion for brand with ID: " + id);

        Optional<BrandEntity> brandEntity = brandRepository.findById(id);
        if (brandEntity.isPresent()) {
            brandRepository.deleteById(id);
            long endTime = System.currentTimeMillis();
            log.info("Brand with ID " + id + " deleted successfully. Total time: " + (endTime - startTime) + " ms");
        } else {
            long endTime = System.currentTimeMillis();
            log.warn("Brand with ID " + id + " not found. Deletion time: " + (endTime - startTime) + " ms");
            throw new Exception("Brand not found");
        }
    }

    @Override
    @Async("taskExecutor")
    public BrandResponse updateBrandById(Integer id, BrandRequest brandRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Starting update for brand with ID: " + id);

        Optional<BrandEntity> brandEntity = brandRepository.findById(id);
        if (brandEntity.isPresent()) {
            Brand updatedBrand = mapToBrand(brandRequest);
            updatedBrand.setId(id);
            brandRepository.save(mapToBrandEntity(updatedBrand));

            long endTime = System.currentTimeMillis();
            log.info("Brand with ID " + id + " updated successfully. Total time: " + (endTime - startTime) + " ms");

            return mapToBrandResponse(updatedBrand);
        } else {
            long endTime = System.currentTimeMillis();
            log.warn("Brand with ID " + id + " not found. Update time: " + (endTime - startTime) + " ms");
            throw new Exception("Brand not found");
        }
    }

    @Override
    @Async("taskExecutor")
    public BrandResponse saveBrand(BrandRequest brandRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Starting save for new brand");

        Brand brand = mapToBrand(brandRequest);
        BrandEntity savedBrandEntity = brandRepository.save(mapToBrandEntity(brand));

        long endTime = System.currentTimeMillis();
        log.info("New brand saved successfully. Total time: " + (endTime - startTime) + " ms");

        return mapToBrandResponse(mapToBrand(savedBrandEntity));
    }

    private Brand mapToBrand(BrandRequest brandRequest) {
        return Brand.builder()
                .name(brandRequest.getName())
                .warranty(brandRequest.getWarranty())
                .country(brandRequest.getCountry())
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

    private BrandEntity mapToBrandEntity(Brand brand) {
        return BrandEntity.builder()
                .id(brand.getId())
                .name(brand.getName())
                .warranty(brand.getWarranty())
                .country(brand.getCountry())
                .build();
    }

    private BrandResponse mapToBrandResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .warranty(brand.getWarranty())
                .country(brand.getCountry())
                .build();
    }
}





