package com.sague.springapp.service;

import com.sague.springapp.controller.dto.BrandRequest;
import com.sague.springapp.controller.dto.BrandResponse;

import java.util.concurrent.CompletableFuture;

public interface BrandService {
    BrandResponse getBrandById(Integer id) throws Exception;
    void deleteBrandById(Integer id) throws Exception;
    BrandResponse updateBrandById(Integer id, BrandRequest brandRequest) throws Exception;
    BrandResponse saveBrand(BrandRequest brandRequest) throws Exception;
}



