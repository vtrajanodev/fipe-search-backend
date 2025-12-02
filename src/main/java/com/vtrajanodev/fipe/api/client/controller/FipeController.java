package com.vtrajanodev.fipe.api.client.controller;

import com.vtrajanodev.fipe.api.client.client.dtos.FipeBrandResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipeModelResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipePriceResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipeYearResponse;
import com.vtrajanodev.fipe.api.client.services.FipeIntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fipe/cars")
public class FipeController {

  private final FipeIntegrationService service;

  public FipeController(FipeIntegrationService service) {
    this.service = service;
  }

  @GetMapping("/brands")
  public ResponseEntity<List<FipeBrandResponse>> listBrands() {
    return ResponseEntity.ok(service.listBrands());
  }

  @GetMapping("/brands/{brandId}/models")
  public ResponseEntity<List<FipeModelResponse>> listModels(@PathVariable String brandId) {
    return ResponseEntity.ok(service.listModels(brandId));
  }

  @GetMapping("/brands/{brandId}/models/{modelId}/years")
  public ResponseEntity<List<FipeYearResponse>> listYears(
          @PathVariable String brandId,
          @PathVariable String modelId
  ) {
    return ResponseEntity.ok(service.listYears(brandId, modelId));
  }

  @GetMapping("/brands/{brandId}/models/{modelId}/years/{yearCode}")
  public ResponseEntity<FipePriceResponse> getPrice(
          @PathVariable String brandId,
          @PathVariable String modelId,
          @PathVariable String yearCode
  ) {
    return ResponseEntity.ok(service.getPrice(brandId, modelId, yearCode));
  }
}
