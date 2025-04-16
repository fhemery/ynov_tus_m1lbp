package org.katas.currency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.katas.currency.external.CurrencyIsoCode;
import org.katas.currency.external.IConversionRateApi;
import org.katas.currency.model.Currency;
import org.katas.currency.model.Money;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CurrencyConverterTest {

    @Mock
    IConversionRateApi IConversionRateApi;

    @Test
    void shouldWork() {
        assertEquals(3, 1+2);
    }

    @Test
    void sum_convertProperly() {
        var converter = new CurrencyConverter(IConversionRateApi);

        Mockito.when(IConversionRateApi.getRate(CurrencyIsoCode.USD, CurrencyIsoCode.EUR)).thenReturn(0.5);

        // This does not work. Make it work please ^_^"
        var result = converter.sum(Currency.Euro, new Money(1, Currency.Dollar));
        assertEquals(0.5, result.amount());
    }

    @Test
    void sum_callConversionApiOnlyOnlyOncePerRate() {
        var converter = new CurrencyConverter(IConversionRateApi);

        Mockito.when(IConversionRateApi.getRate(CurrencyIsoCode.USD, CurrencyIsoCode.EUR)).thenReturn(0.5);

        // This does not work. Make it work please ^_^"
        var result = converter.sum(Currency.Euro, new Money(1, Currency.Dollar), new Money(2, Currency.Dollar));
        assertEquals(1.5, result.amount());

        Mockito.verify(IConversionRateApi, Mockito.times(1)).getRate(CurrencyIsoCode.USD, CurrencyIsoCode.EUR);
    }
}