package com.vtrajanodev.fipe.api.client.controller;

import com.vtrajanodev.fipe.api.client.client.dtos.*;
import com.vtrajanodev.fipe.api.client.services.FipeIntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fipe")
public class FipeController {

  private final FipeIntegrationService service;

  public FipeController(FipeIntegrationService service) {
    this.service = service;
  }

  @GetMapping("/{vehicleType}/brands")
  public ResponseEntity<List<FipeItemResponse>> listBrands(@PathVariable String vehicleType) {
    return ResponseEntity.ok(service.listBrands(vehicleType));
  }

  @GetMapping("/{vehicleType}/brands/{brandId}/models")
  public ResponseEntity<List<FipeItemResponse>> listModels(
          @PathVariable String vehicleType,
          @PathVariable String brandId) {
    return ResponseEntity.ok(service.listModels(vehicleType, brandId));
  }

  @GetMapping("/{vehicleType}/brands/{brandId}/models/{modelId}/years")
  public ResponseEntity<List<FipeItemResponse>> listYears(
          @PathVariable String vehicleType,
          @PathVariable String brandId,
          @PathVariable String modelId) {
    return ResponseEntity.ok(service.listYears(vehicleType, brandId, modelId));
  }

  @GetMapping("/{vehicleType}/brands/{brandId}/models/{modelId}/years/{yearId}")
  public ResponseEntity<FipePriceResponse> getPrice(
          @PathVariable String vehicleType,
          @PathVariable String brandId,
          @PathVariable String modelId,
          @PathVariable String yearId) {
    return ResponseEntity.ok(service.getPriceByYear(vehicleType, brandId, modelId, yearId));
  }

  @GetMapping("/{vehicleType}/brands/{brandId}/models/{modelId}/history")
  public ResponseEntity<List<VehicleFipeInformationResponse>> getPriceHistory(
          @PathVariable String vehicleType,
          @PathVariable String brandId,
          @PathVariable String modelId) {
    return ResponseEntity.ok(service.getPriceHistoryDetailed(vehicleType, brandId, modelId));
  }
}
