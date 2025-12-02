package com.vtrajanodev.fipe.api.client.client.dtos;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FipePriceResponse {
  private String brand;
  private String codeFipe;
  private String fuel;
  private String fuelAcronym;
  private String model;
  private Integer modelYear;
  private String price;
  private List<VehiclePriceHistory> priceHistory;
  private String referenceMonth;
  private String vehicleType;
}
