package com.vtrajanodev.fipe.api.client.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FipeItemResponse {
  private String code;
  private String name;
}
