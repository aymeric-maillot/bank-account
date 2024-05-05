package com.votybe.bankaccount.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Service
public class OpenExchangeCurrencyConverter implements CurrencyConverter {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    @Autowired
    public OpenExchangeCurrencyConverter(ExchangeConverterConfig config, RestTemplate restTemplate) {
        this.baseUrl = config.getBaseUrl() + config.getApiKey();
        this.restTemplate = restTemplate;
    }

    @Override
    public double convert(String fromCurrency, String toCurrency, double amount) {
        String formattedAmount = formatAmount(amount);
        String url = buildConversionUrl(fromCurrency, toCurrency, formattedAmount);

        ConversionRateResponse response = sendRequest(url);

        if (response != null && response.getConversion_rate() != null) {
            return amount * response.getConversion_rate();
        } else {
            throw new RuntimeException("Taux de conversion non disponible.");
        }
    }

    private String formatAmount(double amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);
        decimalFormat.setGroupingUsed(false);
        return decimalFormat.format(amount);
    }

    private String buildConversionUrl(String fromCurrency, String toCurrency, String formattedAmount) {
        return String.format("%s/pair/%s/%s/%s", baseUrl, fromCurrency, toCurrency, formattedAmount);
    }

    private ConversionRateResponse sendRequest(String url) {
        try {
            return restTemplate.getForObject(url, ConversionRateResponse.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Erreur lors de la récupération du taux de conversion.", e);
        }
    }
}
