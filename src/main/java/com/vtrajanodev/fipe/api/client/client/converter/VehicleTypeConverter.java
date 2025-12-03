package com.vtrajanodev.fipe.api.client.client.converter;

import com.vtrajanodev.fipe.api.client.client.enums.VehicleType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeConverter implements Converter<String, VehicleType> {

  @Override
  public VehicleType convert(String source) {
    try {
      return VehicleType.valueOf(source.toUpperCase());
    } catch (IllegalArgumentException e) {
      for (VehicleType type : VehicleType.values()) {
        if (type.getValue().equalsIgnoreCase(source)) {
          return type;
        }
      }
      throw new IllegalArgumentException("Tipo de veículo inválido: " + source);
    }
  }
}
