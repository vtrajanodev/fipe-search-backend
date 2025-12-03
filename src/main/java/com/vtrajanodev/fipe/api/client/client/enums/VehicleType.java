package com.vtrajanodev.fipe.api.client.client.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum VehicleType {
  CARS("cars"),
  MOTORCYCLES("motorcycles"),
  TRUCKS("trucks");

  private final String value;

  VehicleType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @JsonCreator
  public static VehicleType fromValue(String value) {
    for (VehicleType type : VehicleType.values()) {
      if (type.value.equalsIgnoreCase(value)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Invalid vehicle type: " + value);
  }
}
