package com.inteligo.application.data.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CurrencyItem {

  private final String code;
  private final String name;
}
