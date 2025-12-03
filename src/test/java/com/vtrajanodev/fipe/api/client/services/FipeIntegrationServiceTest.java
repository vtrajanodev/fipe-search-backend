package com.vtrajanodev.fipe.api.client.services;

import com.vtrajanodev.fipe.api.client.client.FipeApiClient;
import com.vtrajanodev.fipe.api.client.client.dtos.FipeItemResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipePriceResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.VehicleFipeInformationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class FipeIntegrationServiceTest {

  @Mock
  private FipeApiClient client;

  @InjectMocks
  private FipeIntegrationService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testListBrands() {
    List<FipeItemResponse> mockBrands = new ArrayList<>(List.of(new FipeItemResponse("1", "Brand A"), new FipeItemResponse("2", "Brand B")));
    when(client.getBrands("cars")).thenReturn(mockBrands);

    List<FipeItemResponse> result = service.listBrands("cars");

    assertEquals(2, result.size());
    assertEquals("Brand A", result.get(0).getName());
    verify(client, times(1)).getBrands("cars");
  }

  @Test
  void testListModels() {
    List<FipeItemResponse> mockModels = new ArrayList<>(List.of(new FipeItemResponse("10", "Model X"), new FipeItemResponse("20", "Model Y")));
    when(client.getModelsByBrand("cars", "1")).thenReturn(mockModels);

    List<FipeItemResponse> result = service.listModels("cars", "1");

    assertEquals(2, result.size());
    assertEquals("Model X", result.get(0).getName());
    verify(client, times(1)).getModelsByBrand("cars", "1");
  }

  @Test
  void testGetPriceByYear() {
    FipePriceResponse mockPrice = FipePriceResponse.builder().price("R$ 50.000,00").build();
    when(client.getPriceByYear("cars", "1", "10", "2023")).thenReturn(mockPrice);

    FipePriceResponse result = service.getPriceByYear("cars", "1", "10", "2023");

    assertEquals("R$ 50.000,00", result.getPrice());
    verify(client, times(1)).getPriceByYear("cars", "1", "10", "2023");
  }

  @Test
  void testGetPriceHistoryDetailed() {
    List<FipeItemResponse> mockYears = new ArrayList<>(List.of(new FipeItemResponse("2022-1", "2022 Gasoline"), new FipeItemResponse("2023-1", "2023 Gasoline")));
    when(client.getYearsByModel("cars", "1", "10")).thenReturn(mockYears);

    when(client.getPriceByYear("cars", "1", "10", "2022-1")).thenReturn(FipePriceResponse.builder().price("R$ 40.000,00").build());
    when(client.getPriceByYear("cars", "1", "10", "2023-1")).thenReturn(FipePriceResponse.builder().price("R$ 45.000,00").build());

    List<VehicleFipeInformationResponse> history = service.getPriceHistoryDetailed("cars", "1", "10");

    assertEquals(2, history.size());

    VehicleFipeInformationResponse newest = history.get(0);
    assertEquals("2023", newest.getYear());
    assertEquals("R$ 45.000,00", newest.getPrice());
    assertEquals("R$ 5.000,00", newest.getDiff());
    assertEquals("12,50%", newest.getDiffPercentage());
    assertEquals("2022", newest.getPreviousYear());
    assertEquals("R$ 40.000,00", newest.getPreviousPrice());

    VehicleFipeInformationResponse older = history.get(1);
    assertEquals("2022", older.getYear());
    assertNull(older.getDiff());
    assertNull(older.getDiffPercentage());
    assertNull(older.getPreviousYear());
    assertNull(older.getPreviousPrice());

    verify(client, times(1)).getYearsByModel("cars", "1", "10");
    verify(client, times(1)).getPriceByYear("cars", "1", "10", "2022-1");
    verify(client, times(1)).getPriceByYear("cars", "1", "10", "2023-1");
  }
}
