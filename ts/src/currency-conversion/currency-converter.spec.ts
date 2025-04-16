import { ConversionRateApi } from "./external/conversion-rate-api";
import { CurrencyConverter } from "./currency-converter";
import { Currency } from "./model/currency";
import { Money } from "./model/money";

describe("CurrencyConverter", function () {
  it("is initialized", () => {
    const converter = new CurrencyConverter(new ConversionRateApi());
    expect(converter).toBeTruthy();
  });
  describe("when using mocks", () => {
    it("should convert", () => {
      const api = new ConversionRateApi();
      const converter = new CurrencyConverter(api);

      jest.spyOn(api, "getRate").mockReturnValue(0.5);

      const result = converter.sum(
        Currency.Euro,
        new Money(2, Currency.Dollar)
      );

      expect(result.currency).toBe(Currency.Euro);
      expect(result.amount).toBe(1);
    });

    it("should not call twice the API if rate is already known", () => {
      const api = new ConversionRateApi();
      const converter = new CurrencyConverter(api);

      const spy = jest.spyOn(api, "getRate").mockReturnValue(0.5);

      const result = converter.sum(
        Currency.Euro,
        new Money(2, Currency.Dollar),
        new Money(1, Currency.Dollar)
      );

      expect(result.currency).toBe(Currency.Euro);
      expect(result.amount).toBe(1.5);
      expect(spy).toHaveBeenCalledTimes(1);
    });
  });

  describe("when using pure duck typing", () => {
    it("should convert", () => {
      const api = {
        getRate: jest.fn().mockReturnValue(0.5),
      } as ConversionRateApi;
      const converter = new CurrencyConverter(api);

      const result = converter.sum(
        Currency.Euro,
        new Money(2, Currency.Dollar)
      );

      expect(result.currency).toBe(Currency.Euro);
      expect(result.amount).toBe(1);
    });
  });
});
