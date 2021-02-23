package com.inteligo.application.data.service;

import com.inteligo.application.api.atm.AtmConnector;
import com.inteligo.application.data.entity.Atm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtmServiceImpl implements AtmService {

  private final AtmConnector atmConnector;

  @Override
  public List<Atm> getAtmsLocation() {


    return atmConnector.getAtmLocations()
        .blockFirst()
        .stream()
        .map(atmResponse -> Atm.builder()
            .city(atmResponse.getCity())
            .latitude(atmResponse.getLatitude())
            .longitude(atmResponse.getLongitude())
            .build())
        .collect(Collectors.toList());
  }
}
