package com.inteligo.application.data.service;

import com.inteligo.application.api.exchangeRate.ExchangeRateConnector;
import com.inteligo.application.data.entity.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateConnector currencyConnector;

    @Override
    public ExchangeRate getExchangeRate(String currency) {
        return currencyConnector.getExchangeRate(currency)
            .map(exchangeRateResponse -> ExchangeRate.builder()
                .source(exchangeRateResponse.getSource())
                .code(exchangeRateResponse.getCurrencies().get(0).getCode())
                .rate(""+exchangeRateResponse.getCurrencies().get(0).getRate())
                .build())
            .block();
    }
}
