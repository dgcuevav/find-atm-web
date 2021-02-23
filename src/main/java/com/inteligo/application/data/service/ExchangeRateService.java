package com.inteligo.application.data.service;

import com.inteligo.application.data.entity.ExchangeRate;

public interface ExchangeRateService  {

  ExchangeRate getExchangeRate(String currency);

}