package com.vtrajanodev.fipe.api.client.client;

import com.vtrajanodev.fipe.api.client.client.dtos.FipeBrandResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipeModelResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipePriceResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipeYearResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "fipeClient",
        url = "https://parallelum.com.br/fipe/api/v2"
)
public interface FipeApiClient {

  @GetMapping("/cars/brands")
  List<FipeBrandResponse> getBrands();


  @GetMapping("/cars/brands/{brandId}/models")
  List<FipeModelResponse> getModelsByBrand(@PathVariable("brandId") String brandId);

  @GetMapping("/cars/brands/{brandId}/models/{modelId}/years")
  List<FipeYearResponse> getYearsByModel(
          @PathVariable("brandId") String brandId,
          @PathVariable("modelId") String modelId
  );

  @GetMapping("/cars/brands/{brandId}/models/{modelId}/years/{yearCode}")
  FipePriceResponse getPriceByYear(
          @PathVariable("brandId") String brandId,
          @PathVariable("modelId") String modelId,
          @PathVariable("yearCode") String yearCode
  );
}
