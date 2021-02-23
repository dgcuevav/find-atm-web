package com.inteligo.application.api.exchangeRate;

import com.inteligo.application.data.dto.ExchangeRateResponse;
import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
@Slf4j
public class ExchangeRateConnectorImpl implements ExchangeRateConnector {

  @Value("${baseurl.exchange-rate}")
  private String basUrl;
  private ExchangeRateConfig exchangeRateConfig;

  @Override
  public Mono<ExchangeRateResponse> getExchangeRate(String currency) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(basUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ReactorCallAdapterFactory.create())
        .build();

    exchangeRateConfig = retrofit.create(ExchangeRateConfig.class);
    log.info("URL: {} CURRENCY = {}", basUrl, currency);
    return exchangeRateConfig.getAllRates(currency);
  }
}
