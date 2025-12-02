package com.vtrajanodev.fipe.api.client.client.dtos;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FipeYearResponse {
  private String code; // ex: "2013-1"
  private String name; // ex: "2013 Gasolina"
}
