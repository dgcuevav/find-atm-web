package com.inteligo.application.data.dto.atm;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class AtmResponse {

private final String street;
private final String houseNumber;
private final String postalCode;
private final String city;
private final Double latitude;
private final Double longitude;

}
