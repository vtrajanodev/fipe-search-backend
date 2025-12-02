package com.vtrajanodev.fipe.api.client.client.dtos;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FipeModelResponse {
  private String code;
  private String name;
}