package com.inteligo.application.api.atm;

import com.inteligo.application.data.dto.atm.AtmResponse;
import reactor.core.publisher.Flux;
import retrofit2.http.GET;

import java.util.List;

public interface AtmConfig {

  @GET("/atm")
  Flux<List<AtmResponse>> getAtmLocations();
}
