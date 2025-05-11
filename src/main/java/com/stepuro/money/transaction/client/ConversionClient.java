package com.stepuro.money.transaction.client;

import com.stepuro.money.transaction.model.ConversionHistoricalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(value = "conversionClient", url = "${conversion.url}")
public interface ConversionClient {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v3/historical",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ConversionHistoricalResponse getHistoricalData(
            @RequestHeader("apiKey") final String key,
            @RequestParam("date") final LocalDate date,
            @RequestParam(value = "base_currency", required = false) final String currency
    );
}
