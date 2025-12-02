package com.vtrajanodev.fipe.api.client.client.dtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FipeItemResponse {
  private String code;
  private String name;
}

