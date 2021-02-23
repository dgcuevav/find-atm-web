package com.inteligo.application.api.atm;

import com.inteligo.application.data.dto.atm.AtmResponse;
import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

@Component
@Slf4j
public class AtmConnectorImpl implements AtmConnector {

  @Value("${baseurl.atm}")
  private String basUrl;
  private AtmConfig atmConfig;

  @Override
  public Flux<List<AtmResponse>> getAtmLocations() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(basUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ReactorCallAdapterFactory.create())
        .build();

    atmConfig = retrofit.create(AtmConfig.class);
    log.info("start call to : {} ", basUrl);
    return atmConfig.getAtmLocations();
  }
}
