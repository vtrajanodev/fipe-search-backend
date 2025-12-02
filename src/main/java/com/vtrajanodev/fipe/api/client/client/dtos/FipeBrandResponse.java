package com.vtrajanodev.fipe.api.client.client.dtos;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FipeBrandResponse {
  private String code;
  private String name;
}
