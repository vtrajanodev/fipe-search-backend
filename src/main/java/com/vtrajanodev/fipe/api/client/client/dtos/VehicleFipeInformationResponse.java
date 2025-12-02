package com.vtrajanodev.fipe.api.client.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleFipeInformationResponse {
  private String year;
  private String price;
  private String diff;
  private String diffPercentage;
  private String previousYear;
  private String previousPrice;
}
