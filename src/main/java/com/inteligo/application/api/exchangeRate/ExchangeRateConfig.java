package com.inteligo.application.api.exchangeRate;

import com.inteligo.application.data.dto.exchangeRate.ExchangeRateResponse;
import reactor.core.publisher.Mono;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExchangeRateConfig {

  @GET("/exchange-rate")
  Mono<ExchangeRateResponse> getAllRates(@Query("currencies") String currencyCode);

}
