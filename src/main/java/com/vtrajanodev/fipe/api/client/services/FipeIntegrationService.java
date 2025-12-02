package com.vtrajanodev.fipe.api.client.services;

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

  public List<FipeBrandResponse> listBrands() {
    return client.getBrands();
  }

  public List<FipeModelResponse> listModels(String brandId) {
    return client.getModelsByBrand(brandId);
  }

  public List<FipeYearResponse> listYears(String brandId, String modelId) {
    return client.getYearsByModel(brandId, modelId);
  }

  public FipePriceResponse getPriceByYear(String brandId, String modelId, String yearId) {
    return client.getPriceByYear(brandId, modelId, yearId);
  }

  public List<VehiclePriceHistory> getPriceHistory(String brandId, String modelId) {
    List<FipeYearResponse> years = client.getYearsByModel(brandId, modelId);

    years.sort(Comparator.comparing(FipeYearResponse::getName));

    List<VehiclePriceHistory> history = new ArrayList<>();
    BigDecimal previousPrice = null;

    for (FipeYearResponse year : years) {
      FipePriceResponse priceResponse = client.getPriceByYear(brandId, modelId, year.getCode());
      String price = priceResponse.getPrice();

      BigDecimal currentPrice = parsePrice(price);

      VehiclePriceHistory priceHistory = VehiclePriceHistory.builder()
              .year(year.getName())
              .price(price)
              .build();

      if (previousPrice != null) {
        BigDecimal priceDiff = currentPrice.subtract(previousPrice);
        BigDecimal diffPercentage = previousPrice.compareTo(BigDecimal.ZERO) != 0
                ? priceDiff.divide(previousPrice, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
                : BigDecimal.ZERO;

        priceHistory = VehiclePriceHistory.builder()
                .year(year.getName())
                .price(price)
                .diff(priceDiff)
                .diffPercentage(diffPercentage)
                .build();
      }

      previousPrice = currentPrice;
      history.add(priceHistory);
    }

    history.sort(Comparator.comparing(VehiclePriceHistory::getYear).reversed());

    return history;
  }

  private BigDecimal parsePrice(String price) {
    if (price == null || price.isBlank()) {
      return BigDecimal.ZERO;
    }

    String clean = price.replace("R$", "").replace(".", "").replace(",", ".").trim();

    try {
      return new BigDecimal(clean);
    } catch (NumberFormatException e) {
      return BigDecimal.ZERO;
    }
  }
}

