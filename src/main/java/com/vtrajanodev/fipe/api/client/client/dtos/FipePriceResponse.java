package com.vtrajanodev.fipe.api.client.client.dtos;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FipePriceResponse {
  private String Valor;
  private String Marca;
  private String Modelo;
  private Integer AnoModelo;
  private String Combustivel;
  private String CodigoFipe;
  private String MesReferencia;
  private String TipoVeiculo;
  private String SiglaCombustivel;
}
