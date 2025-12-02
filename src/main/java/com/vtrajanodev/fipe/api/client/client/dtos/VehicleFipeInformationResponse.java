package com.vtrajanodev.fipe.api.client.client.dtos;

import lombok.*;

import java.math.BigDecimal;

@Data
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
