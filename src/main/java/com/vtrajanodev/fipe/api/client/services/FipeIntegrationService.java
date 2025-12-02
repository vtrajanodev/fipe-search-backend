package com.vtrajanodev.fipe.api.client.services;

import com.vtrajanodev.fipe.api.client.client.FipeApiClient;
import com.vtrajanodev.fipe.api.client.client.dtos.FipeBrandResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipeModelResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipePriceResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipeYearResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FipeIntegrationService {

  private final FipeApiClient client;

  public FipeIntegrationService(FipeApiClient client) {
    this.client = client;
  }

  public List<FipeBrandResponse> listBrands() {
    return client.getBrands();
  }

  public List<FipeModelResponse> listModels(String brandId) {
    return client.getModelsByBrand(brandId);
  }

  public List<FipeYearResponse> listYears(String brandId, String modelId) {
    return client.getYearsByModel(brandId, modelId);
  }

  public FipePriceResponse getPrice(String brandId, String modelId, String yearCode) {
    return client.getPriceByYear(brandId, modelId, yearCode);
  }
}

