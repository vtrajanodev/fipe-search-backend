package com.vtrajanodev.fipe.api.client.controller;

import com.vtrajanodev.fipe.api.client.client.dtos.FipeItemResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipePriceResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.VehicleFipeInformationResponse;
import com.vtrajanodev.fipe.api.client.services.FipeIntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/fipe")
public class FipeController {

  private final FipeIntegrationService service;

  @GetMapping("/{vehicleType}/brands")
  public ResponseEntity<List<FipeItemResponse>> listBrands(@PathVariable String vehicleType) {
    log.info("Consultando marcas de veículo {}", vehicleType);
    return ResponseEntity.ok(service.listBrands(vehicleType));
  }

  @GetMapping("/{vehicleType}/brands/{brandId}/models")
  public ResponseEntity<List<FipeItemResponse>> listModels(
          @PathVariable String vehicleType,
          @PathVariable String brandId) {
    log.info("Consultando modelos de veículo referente a marca {}", brandId);
    return ResponseEntity.ok(service.listModels(vehicleType, brandId));
  }

  @GetMapping("/{vehicleType}/brands/{brandId}/models/{modelId}/years/{yearId}")
  public ResponseEntity<FipePriceResponse> getPrice(
          @PathVariable String vehicleType,
          @PathVariable String brandId,
          @PathVariable String modelId,
          @PathVariable String yearId) {
    log.info("Consultando preço por modelo de veículo");
    return ResponseEntity.ok(service.getPriceByYear(vehicleType, brandId, modelId, yearId));
  }

  @GetMapping("/{vehicleType}/brands/{brandId}/models/{modelId}/history")
  public ResponseEntity<List<VehicleFipeInformationResponse>> getPriceHistory(
          @PathVariable String vehicleType,
          @PathVariable String brandId,
          @PathVariable String modelId) {
    log.info("Gerando histórico completo de veículo por ano");
    return ResponseEntity.ok(service.getPriceHistoryDetailed(vehicleType, brandId, modelId));
  }
}
