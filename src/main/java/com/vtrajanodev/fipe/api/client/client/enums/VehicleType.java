package com.vtrajanodev.fipe.api.client.client.enums;

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
}
