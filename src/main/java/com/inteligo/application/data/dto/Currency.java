package com.inteligo.application.data.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Currency {
  private final String code;
  private final Double rate;
}
