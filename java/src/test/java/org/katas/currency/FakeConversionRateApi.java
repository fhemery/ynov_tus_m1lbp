package org.katas.currency;

import org.katas.currency.external.CurrencyIsoCode;
import org.katas.currency.external.IConversionRateApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeConversionRateApi implements IConversionRateApi {
    List<String> calls = new ArrayList<>();
    HashMap<String, Double> rates = new HashMap<>();

    @Override
    public double getRate(CurrencyIsoCode source, CurrencyIsoCode target) {
        calls.add(source+"-"+target);
        if (rates.containsKey(source+"-"+target)) {
            return rates.get(source + "-" + target);
        }
        throw new RuntimeException("Rate does not exist");
    }

    public FakeConversionRateApi withRate(CurrencyIsoCode from, CurrencyIsoCode to, double rate) {
        rates.put(from+"-"+to, rate);
        return this;
    }
}
