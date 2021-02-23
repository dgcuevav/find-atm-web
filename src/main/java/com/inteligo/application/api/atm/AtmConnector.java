package com.inteligo.application.api.atm;

import com.inteligo.application.data.dto.atm.AtmResponse;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AtmConnector {
  Flux<List<AtmResponse>> getAtmLocations();
}
