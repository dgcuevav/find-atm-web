package com.inteligo.application.api.exchangeRate;

import com.inteligo.application.data.dto.exchangeRate.ExchangeRateResponse;
import reactor.core.publisher.Mono;

public interface ExchangeRateConnector {
  Mono<ExchangeRateResponse> getExchangeRate(String currency);
}
