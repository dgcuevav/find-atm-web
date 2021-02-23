package com.inteligo.application.data.dto.exchangeRate;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class ExchangeRateResponse {

  private final String source;
  private final List<Currency> currencies;

}
