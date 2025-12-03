package com.vtrajanodev.fipe.api.client.controller;

import com.vtrajanodev.fipe.api.client.client.dtos.FipeItemResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipePriceResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.VehicleFipeInformationResponse;
import com.vtrajanodev.fipe.api.client.services.FipeIntegrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FipeController.class)
class FipeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private FipeIntegrationService service;

  @Test
  void testListBrands() throws Exception {
    List<FipeItemResponse> mockBrands = List.of(
            new FipeItemResponse("1", "Ford"),
            new FipeItemResponse("2", "Chevrolet")
    );

    when(service.listBrands("cars")).thenReturn(mockBrands);

    mockMvc.perform(get("/fipe/cars/brands")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].code").value("1"))
            .andExpect(jsonPath("$[0].name").value("Ford"))
            .andExpect(jsonPath("$[1].code").value("2"))
            .andExpect(jsonPath("$[1].name").value("Chevrolet"));

    verify(service, times(1)).listBrands("cars");
  }

  @Test
  void testListModels() throws Exception {
    List<FipeItemResponse> mockModels = List.of(
            new FipeItemResponse("10", "Fiesta"),
            new FipeItemResponse("20", "Focus")
    );

    when(service.listModels("cars", "1")).thenReturn(mockModels);

    mockMvc.perform(get("/fipe/cars/brands/1/models")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].code").value("10"))
            .andExpect(jsonPath("$[0].name").value("Fiesta"))
            .andExpect(jsonPath("$[1].code").value("20"))
            .andExpect(jsonPath("$[1].name").value("Focus"));

    verify(service, times(1)).listModels("cars", "1");
  }

  @Test
  void testGetPrice() throws Exception {
    FipePriceResponse mockResponse = FipePriceResponse.builder()
            .brand("Ford")
            .codeFipe("001234")
            .fuel("Gasolina")
            .fuelAcronym("G")
            .model("Fiesta")
            .modelYear(2019)
            .price("45000")
            .referenceMonth("novembro/2024")
            .vehicleType("cars")
            .build();

    when(service.getPriceByYear("cars", "1", "10", "2019"))
            .thenReturn(mockResponse);

    mockMvc.perform(get("/fipe/cars/brands/1/models/10/years/2019")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.modelYear").value(2019))
            .andExpect(jsonPath("$.price").value("45000"))
            .andExpect(jsonPath("$.model").value("Fiesta"));

    verify(service, times(1))
            .getPriceByYear("cars", "1", "10", "2019");
  }
  @Test
  void testGetPriceHistory() throws Exception {
    List<VehicleFipeInformationResponse> mockHistory = List.of(
            VehicleFipeInformationResponse.builder()
                    .year("2018")
                    .price("40000")
                    .diff(null)
                    .diffPercentage(null)
                    .previousYear(null)
                    .previousPrice(null)
                    .build(),
            VehicleFipeInformationResponse.builder()
                    .year("2019")
                    .price("45000")
                    .diff(null)
                    .diffPercentage(null)
                    .previousYear(null)
                    .previousPrice(null)
                    .build()
    );

    when(service.getPriceHistoryDetailed("cars", "1", "10"))
            .thenReturn(mockHistory);

    mockMvc.perform(get("/fipe/cars/brands/1/models/10/history")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].year").value("2018"))
            .andExpect(jsonPath("$[0].price").value("40000"))
            .andExpect(jsonPath("$[1].year").value("2019"))
            .andExpect(jsonPath("$[1].price").value("45000"));

    verify(service, times(1))
            .getPriceHistoryDetailed("cars", "1", "10");
  }
}
