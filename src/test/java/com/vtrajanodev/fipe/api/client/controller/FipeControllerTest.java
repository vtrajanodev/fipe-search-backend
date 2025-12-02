package com.vtrajanodev.fipe.api.client.controller;

import com.vtrajanodev.fipe.api.client.client.dtos.FipeItemResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.FipePriceResponse;
import com.vtrajanodev.fipe.api.client.client.dtos.VehicleFipeInformationResponse;
import com.vtrajanodev.fipe.api.client.services.FipeIntegrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FipeControllerTest {

  @Mock
  private FipeIntegrationService service;

  @InjectMocks
  private FipeController controller;

  @Test
  void testListBrands() {
    List<FipeItemResponse> mockBrands = List.of(
            new FipeItemResponse("1", "Ford"),
            new FipeItemResponse("2", "Chevrolet")
    );

    when(service.listBrands("cars")).thenReturn(mockBrands);

    ResponseEntity<List<FipeItemResponse>> response = controller.listBrands("cars");

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertNotNull(response.getBody());
    assertEquals(2, response.getBody().size());
    assertEquals("Ford", response.getBody().get(0).getName());

    verify(service, times(1)).listBrands("cars");
  }

  @Test
  void testListModels() {
    List<FipeItemResponse> mockModels = List.of(
            new FipeItemResponse("10", "Fiesta"),
            new FipeItemResponse("20", "Focus")
    );

    when(service.listModels("cars", "1")).thenReturn(mockModels);

    ResponseEntity<List<FipeItemResponse>> response = controller.listModels("cars", "1");

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertNotNull(response.getBody());
    assertEquals(2, response.getBody().size());
    assertEquals("Fiesta", response.getBody().get(0).getName());

    verify(service, times(1)).listModels("cars", "1");
  }

  @Test
  void testGetPrice() {
    FipePriceResponse mockPrice = new FipePriceResponse(
            "Ford",
            "001267-0",
            "Gasolina",
            "G",
            "Fiesta",
            2019,
            "45000",
            "setembro de 2024",
            "cars"
    );

    when(service.getPriceByYear("cars", "1", "10", "2019"))
            .thenReturn(mockPrice);

    ResponseEntity<FipePriceResponse> response =
            controller.getPrice("cars", "1", "10", "2019");

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertNotNull(response.getBody());
    FipePriceResponse body = response.getBody();

    assertEquals("Ford", body.getBrand());
    assertEquals("Fiesta", body.getModel());
    assertEquals(2019, body.getModelYear());
    assertEquals("45000", body.getPrice());

    verify(service, times(1))
            .getPriceByYear("cars", "1", "10", "2019");
  }

  @Test
  void testGetPriceHistory() {
    List<VehicleFipeInformationResponse> mockHistory = List.of(
            new VehicleFipeInformationResponse(
                    "2018",
                    "40000",
                    null,
                    null,
                    null,
                    null
            ),
            new VehicleFipeInformationResponse(
                    "2019",
                    "45000",
                    null,
                    null,
                    null,
                    null
            )
    );

    when(service.getPriceHistoryDetailed("cars", "1", "10"))
            .thenReturn(mockHistory);

    ResponseEntity<List<VehicleFipeInformationResponse>> response =
            controller.getPriceHistory("cars", "1", "10");

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertNotNull(response.getBody());
    assertEquals(2, response.getBody().size());

    assertEquals("2018", response.getBody().get(0).getYear());
    assertEquals("40000", response.getBody().get(0).getPrice());

    assertEquals("2019", response.getBody().get(1).getYear());
    assertEquals("45000", response.getBody().get(1).getPrice());

    verify(service, times(1))
            .getPriceHistoryDetailed("cars", "1", "10");
  }
}


