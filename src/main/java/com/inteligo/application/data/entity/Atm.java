package com.inteligo.application.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Atm {
  private String street;
  private String houseNumber;
  private String postalCode;
  private String city;
  private Double latitude;
  private Double longitude;
}
