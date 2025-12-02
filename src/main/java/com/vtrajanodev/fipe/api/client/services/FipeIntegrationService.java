package com.vtrajanodev.fipe.api.client.services;

import com.vtrajanodev.fipe.api.client.client.FipeApiClient;
import com.vtrajanodev.fipe.api.client.client.dtos.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.vtrajanodev.fipe.api.client.client.FipeApiClient;
import com.vtrajanodev.fipe.api.client.client.dtos.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FipeIntegrationService {

  private final FipeApiClient client;

  public FipeIntegrationService(FipeApiClient client) {
    this.client = client;
  }

  public List<FipeItemResponse> listBrands(String vehicleType) {
    return client.getBrands(vehicleType);
  }

  public List<FipeItemResponse> listModels(String vehicleType, String brandId) {
    return client.getModelsByBrand(vehicleType, brandId);
  }

  public List<FipeItemResponse> listYears(String vehicleType, String brandId, String modelId) {
    return client.getYearsByModel(vehicleType, brandId, modelId);
  }

  public FipePriceResponse getPriceByYear(String vehicleType, String brandId, String modelId, String yearId) {
    return client.getPriceByYear(vehicleType, brandId, modelId, yearId);
  }

  public List<VehicleFipeInformationResponse> getPriceHistoryDetailed(
          String vehicleType, String brandId, String modelId
  ) {
    List<FipeItemResponse> years = client.getYearsByModel(vehicleType, brandId, modelId);
    years.sort(Comparator.comparing(FipeItemResponse::getName));

    List<VehicleFipeInformationResponse> history = new ArrayList<>();
    BigDecimal previousPrice = null;
    String previousYearOnly = null;
    String previousPriceStr = null;

    for (FipeItemResponse yearItem : years) {
      FipePriceResponse priceResponse = client.getPriceByYear(vehicleType, brandId, modelId, yearItem.getCode());
      String price = priceResponse.getPrice();
      BigDecimal currentPrice = parsePrice(price);

      String yearOnly = yearItem.getName().split(" ")[0];

      BigDecimal diff = null;
      BigDecimal diffPercentage = null;

      if (previousPrice != null) {
        diff = currentPrice.subtract(previousPrice);
        diffPercentage = previousPrice.compareTo(BigDecimal.ZERO) != 0
                ? diff.divide(previousPrice, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
                : BigDecimal.ZERO;
      }

      VehicleFipeInformationResponse detail = VehicleFipeInformationResponse.builder()
              .year(yearOnly)
              .price(price)
              .diff(diff)
              .diffPercentage(diffPercentage)
              .previousYear(previousYearOnly)
              .previousPrice(previousPriceStr)
              .build();

      previousPrice = currentPrice;
      previousYearOnly = yearOnly;
      previousPriceStr = price;

      history.add(detail);
    }

    history.sort(Comparator.comparing(VehicleFipeInformationResponse::getYear).reversed());
    return history;
  }

  private BigDecimal parsePrice(String price) {
    if (price == null || price.isBlank()) return BigDecimal.ZERO;
    String clean = price.replace("R$", "").replace(".", "").replace(",", ".").trim();
    try {
      return new BigDecimal(clean);
    } catch (NumberFormatException e) {
      return BigDecimal.ZERO;
    }
  }
}

