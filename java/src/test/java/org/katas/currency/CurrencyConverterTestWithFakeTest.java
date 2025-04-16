package org.katas.currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.katas.currency.external.CurrencyIsoCode;
import org.katas.currency.model.Currency;
import org.katas.currency.model.Money;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyConverterTestWithFakeTest {
    CurrencyConverter converter;
    FakeConversionRateApi fakeApi;

    @BeforeEach
    public void setup() {
        fakeApi = new FakeConversionRateApi();
        converter = new CurrencyConverter(fakeApi);
    }

    @Test
    public void shouldConvertOneRate() {
        fakeApi.withRate(CurrencyIsoCode.USD, CurrencyIsoCode.EUR, 0.5);
        var result = converter.sum(Currency.Euro, new Money(1, Currency.Dollar));

        assertEquals(0.5, result.amount());
        assertEquals(Currency.Euro, result.currency());
    }

    @Test
    public void shouldCallApiOnlyOncePerRate() {
        fakeApi.withRate(CurrencyIsoCode.USD, CurrencyIsoCode.EUR, 0.5);
        converter.sum(Currency.Euro, new Money(1, Currency.Dollar), new Money(2, Currency.Dollar));

        assertEquals(1, fakeApi.calls.size());
        assertEquals("USD-EUR", fakeApi.calls.get(0));
    }

    @Test
    public void shouldCallApiOnDifferentRates() {
        var dollarToEuroRate = 0.5;
        var yenToEuroRate = 0.01;
        fakeApi.withRate(CurrencyIsoCode.USD, CurrencyIsoCode.EUR, dollarToEuroRate);
        fakeApi.withRate(CurrencyIsoCode.JPY, CurrencyIsoCode.EUR, yenToEuroRate);
        var result = converter.sum(Currency.Euro, new Money(1, Currency.Dollar), new Money(100, Currency.Yen));

        assertEquals( 1 * dollarToEuroRate + yenToEuroRate * 100,  result.amount());
    }
}
