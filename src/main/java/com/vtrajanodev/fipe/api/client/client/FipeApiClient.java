package com.vtrajanodev.fipe.api.client.client;

import com.vtrajanodev.fipe.api.client.client.dtos.FipeItemResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipePriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "fipeClient",
        url = "${fipe.api.url}"
)
public interface FipeApiClient {
  @GetMapping("/{vehicleType}/brands")
  List<FipeItemResponse> getBrands(@PathVariable("vehicleType") String vehicleType);

  @GetMapping("/{vehicleType}/brands/{brandId}/models")
  List<FipeItemResponse> getModelsByBrand(
          @PathVariable("vehicleType") String vehicleType,
          @PathVariable("brandId") String brandId
  );

  @GetMapping("/{vehicleType}/brands/{brandId}/models/{modelId}/years")
  List<FipeItemResponse> getYearsByModel(
          @PathVariable("vehicleType") String vehicleType,
          @PathVariable("brandId") String brandId,
          @PathVariable("modelId") String modelId
  );

  @GetMapping("/{vehicleType}/brands/{brandId}/models/{modelId}/years/{yearId}")
  FipePriceResponse getPriceByYear(
          @PathVariable("vehicleType") String vehicleType,
          @PathVariable("brandId") String brandId,
          @PathVariable("modelId") String modelId,
          @PathVariable("yearId") String yearCode
  );
}
