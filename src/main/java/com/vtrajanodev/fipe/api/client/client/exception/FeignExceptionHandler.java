package com.vtrajanodev.fipe.api.client.client.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class FeignExceptionHandler {

  @ExceptionHandler(FeignException.NotFound.class)
  public ResponseEntity<Map<String, Object>> handleNotFound(FeignException.NotFound ex) {
    Map<String, Object> body = Map.of(
            "status", 404,
            "error", "Resource not found",
            "message", "Resource not found"
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(FeignException.InternalServerError.class)
  public ResponseEntity<Map<String, Object>> handleInternalServerError(FeignException.InternalServerError ex) {
    Map<String, Object> body = Map.of(
            "status", 500,
            "error", "Internal server error",
            "message", "Erro na API externa. Tente novamente mais tarde."
    );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }

  @ExceptionHandler(FeignException.class)
  public ResponseEntity<Map<String, Object>> handleOtherFeignErrors(FeignException ex) {
    Map<String, Object> body = Map.of(
            "status", ex.status(),
            "error", "Erro na API externa",
            "message", ex.getMessage()
    );
    return ResponseEntity.status(ex.status()).body(body);
  }
}

