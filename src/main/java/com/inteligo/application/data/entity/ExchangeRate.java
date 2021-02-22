package com.inteligo.application.data.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class ExchangeRate {
  private final String source;
  private final List<Currency> currencies;

}
